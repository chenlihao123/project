package com.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author chenlihao
 * @create 2020-11-26 18:29
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
    //用于接收子类(enctype="multipart/form-data")的post请求的值
    protected List<FileItem> list = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //不是多段数据action会直接被赋值
        String action = request.getParameter("action");
        if(action==null){//没被赋值说明是多段数据
            //1 先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传的）
            if (ServletFileUpload.isMultipartContent(request)) {
                // 创建 FileItemFactory 工厂实现类
                FileItemFactory fileItemFactory = new DiskFileItemFactory();
                // 创建用于解析上传数据的工具类 ServletFileUpload 类
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
                try {
                    list = servletFileUpload.parseRequest(request);
                    for (FileItem item : list) {
                        if ("action".equals(item.getFieldName())) {
                            action = item.getString("UTF-8");
                        }
                    }
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            //获取方法
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
