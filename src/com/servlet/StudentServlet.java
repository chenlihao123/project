package com.servlet;

import com.entity.Student;
import com.google.gson.Gson;
import com.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenlihao
 * @create 2020-12-09 15:37
 */
@WebServlet("/stu.do")
public class StudentServlet extends HttpServlet {
    private StudentServiceImpl studentService=new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request,response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取用户名密码，查找对应学生
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Student student = studentService.login(username, password);
        int LoginMsg=0;
        if (student!=null){//如果学生查找到，返回1，代表登陆成功
            LoginMsg=1;
        }
        response.getWriter().write(LoginMsg);
    }
}
