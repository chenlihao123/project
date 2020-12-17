package com.servlet;

import com.entity.Course;
import com.entity.CourseGrade;
import com.entity.CourseVideo;
import com.entity.Student;
import com.google.gson.Gson;
import com.service.impl.*;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-09 15:37
 */
@WebServlet("/stu.do")
public class StudentServlet extends BaseServlet {
    private StudentServiceImpl studentService = new StudentServiceImpl();
    private CourseVideoServiceImpl courseVideoService=new CourseVideoServiceImpl();
    private StuCourseServiceImpl stuCourseService=new StuCourseServiceImpl();
    private CourseServiceImpl courseService=new CourseServiceImpl();
    private CourseGradeServiceImpl courseGradeService=new CourseGradeServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Student student = studentService.login(username, password);
        String LoginMsg = "登录失败";
        if (student != null) {//如果学生查找到，返回1，代表登陆成功
            LoginMsg = "登录成功";
            //将学生放入session域当众
            request.getSession().setAttribute("student", student);
        }
        response.getWriter().write(LoginMsg);
    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取用户输入的验证码和阿里服务器发送的验证码
        String code = request.getParameter("code");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sms = (String) request.getSession().getAttribute("sendSMS");
        String phone = (String) request.getSession().getAttribute("phone");
        String codeMsg = "验证码错误";//返回客户端验证码是否成功信息
        String registerMsg = "注册失败";//返回客户端注册是否成功信息
        if (sms != null && sms.equals(code)) {
            System.out.println("验证码正确");
            codeMsg = "验证码正确";
            //注册用户
            boolean b = studentService.register(new Student(username, password, phone));
            System.out.println(b);
            if (b) {
                registerMsg = "注册成功";
            }
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("codeMsg", codeMsg);
        map.put("registerMsg", registerMsg);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }

    //发送验证码
    public void sendCode(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        String sendSMS = studentService.sendSMS(phone);
        request.getSession().setAttribute("phone",phone);
        request.getSession().setAttribute("sendSMS", sendSMS);//将验证码放到session域当中
    }

    //唯一性校验
    public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        Student student = null;
        String checkMsg="1";//1代表检查合格
        if (phone != null) {
            student = studentService.queryStudentByPhone(phone);
        }
        if (email != null) {
            student = studentService.queryStudentByEmail(email);
        }
        if (username != null) {
            student = studentService.queryStudentByUsername(username);
        }
        if (student!=null){
            checkMsg="0";//如果student不为空，说明用户有重复，0代表不合格
        }
        response.getWriter().write(checkMsg);
    }

    //注销
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/onlinelearning/index.html");//重定向到home首页
    }

    //回填学生所选课程
    public void initCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Student student = (Student) request.getSession().getAttribute("student");
        List<Course> courseList = stuCourseService.queryCourseByStudentId(student.getId());
        Gson gson = new Gson();
        String json = gson.toJson(courseList);
        response.getWriter().write(json);
    }

    //回填视频
    public void initVideo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        Student student = (Student) request.getSession().getAttribute("student");
        List<CourseVideo> courseVideos = courseVideoService.queryVideoByCourseIdAndStudentId(courseId, student.getId());
        Gson gson = new Gson();
        String json = gson.toJson(courseVideos);
        response.getWriter().write(json);
    }

    //回填所有课程
    public void initAllCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Course> courses = courseService.queryAllCourse();
        Gson gson = new Gson();
        String json = gson.toJson(courses);
        response.getWriter().write(json);
    }

    //回填所选课程信息
    public void getChoiceCourseInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        Course course = courseService.queryCourseById(courseId);
        List<CourseGrade> courseGrades = courseGradeService.queryCourseGradeByCourseId(courseId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("courseMsg",course);
        map.put("courseGradeMsg",courseGrades);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }

    //回填所选课程的评价信息
    public void getChoseCourseGrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        List<CourseGrade> courseGrades = courseGradeService.queryCourseGradeByCourseId(courseId);
        Gson gson = new Gson();
        String json = gson.toJson(courseGrades);
        response.getWriter().write(json);
    }

}


