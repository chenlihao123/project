package com.servlet;

import com.alibaba.fastjson.JSON;
import com.dao.impl.AdminDaoImpl;
import com.entity.Admin;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.Total;
import com.google.gson.Gson;
import com.service.impl.StudentServiceImpl;
import com.service.impl.TeacherServiceImpl;
import com.service.impl.TotalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: Fu YuHang
 * 日期: 2020/12/20 22:34
 * 描述:
 */
@WebServlet("/admin.do")
public class AdminServlet extends BaseServlet {
    private StudentServiceImpl studentService = new StudentServiceImpl();
    private TeacherServiceImpl teacherService=new TeacherServiceImpl();
    private TotalServiceImpl totalService=new TotalServiceImpl();
    private AdminDaoImpl adminDao=new AdminDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }
    //返回学生信息
    public void studentList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Student> students = studentService.queryAllStudent();
        Map<Object, Object> dataMap = new HashMap<>();
        int studentCount = students.size();
        dataMap.put("data",students);
        dataMap.put("code",0);
        dataMap.put("msg","");
        dataMap.put("count",studentCount);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(dataMap);
        response.getWriter().write(jsonStr);
    }
    //通过id删除学生
    public void deleteStudentById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        int flag = studentService.deleteStudentById(id);
        response.getWriter().write(flag);
    }
    //发布公告
    public void addNotice(HttpServletRequest request, HttpServletResponse response){
        List<Student> students = studentService.queryAllStudent();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String content = request.getParameter("content");
        for (Student student : students) {
           studentService.addNoticeToStudentByAdmin(student.getId(), "", content, date);
        }
    }
    //添加老师
    public void addTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String phone = request.getParameter("phone");
        String department = request.getParameter("department");
        String school = request.getParameter("school");
        String psd = request.getParameter("psd");
        String sex = request.getParameter("sex");
        String name = request.getParameter("name");
        int i = teacherService.addTeacher(name, phone, school, department, sex, psd);
        response.getWriter().write("添加成功");

    }
    //管理员页面显示老师列表
    public void teachertList(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<Teacher> teachers = teacherService.queryAllTeacher();
        Map<Object, Object> dataMap = new HashMap<>();
        int studentCount = teachers.size();
        dataMap.put("data",teachers);
        dataMap.put("code",0);
        dataMap.put("msg","");
        dataMap.put("count",studentCount);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(dataMap);
        response.getWriter().write(jsonStr);
    }
    //修改学生信息
    public void modifyStudentById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        Integer id = Integer.parseInt(request.getParameter("id"));
        String phone = request.getParameter("phone");
        String department = request.getParameter("department");
        String school = request.getParameter("school");
        String psd = request.getParameter("psd");
        String sex = request.getParameter("sex");
        Student student = studentService.queryStudentById(id);
        student.setPhone(phone);
        student.setGender(sex);
        student.setDepartment(department);
        student.setSchool(school);
        student.setPassword(psd);
        int flag =0;
        boolean b = studentService.updateStuInfo(student);
        if (b) {
            flag =1;
        }
        response.getWriter().write(flag);

    }
    //多字段查询学生
    public void queryStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String school = request.getParameter("school");
        String sex = request.getParameter("sex");
        id = ("").equals(id)? "%":id;
        name = ("").equals(name)? "%":name;
        school = ("").equals(school)? "%":school;
        sex = sex.equals("0") ?  "%" : sex;
        List<Student> students = studentService.queryStudent(id, name, school, sex);
        HashMap<Object, Object> dataMap = new HashMap<>();
        dataMap.put("code", 0);
        dataMap.put("msg","");
        dataMap.put("count",students.size());
        dataMap.put("data", students);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(dataMap);
        response.getWriter().write(jsonStr);
    }
    //通过id删除老师
    public void deleteTeacherById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        int flag = teacherService.deleteTeacherById(id);
        response.getWriter().write(flag);
    }
    //回填total
    public void getTotal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Total total = totalService.queryTotal();
        response.getWriter().write(new Gson().toJson(total));
    }
    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = adminDao.QueryAdminByUsernameAndPassword(username, password);
        String LoginMsg = "0";
        if (admin != null) {//如果学生查找到，返回1，代表登陆成功
            LoginMsg = "1";
            //将学生放入session域当众
            request.getSession().setAttribute("admin", admin);
        }
        response.getWriter().write(LoginMsg);
    }
}
