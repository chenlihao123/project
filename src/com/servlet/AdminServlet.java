package com.servlet;

import com.alibaba.fastjson.JSON;
import com.entity.Student;
import com.google.gson.Gson;
import com.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
}
