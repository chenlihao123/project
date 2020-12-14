package com.servlet;

import com.entity.Course;
import com.entity.CourseVideo;
import com.entity.Homework;
import com.entity.Teacher;
import com.google.gson.Gson;
import com.service.impl.CourseServiceImpl;
import com.service.impl.CourseVideoServiceImpl;
import com.service.impl.HomeworkServiceImpl;
import com.service.impl.TeacherServiceImpl;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenlihao
 * @create 2020-12-11 9:21
 */
@WebServlet("/teacher.do")
public class TeacherServlet extends BaseServlet {
    private CourseServiceImpl courseService=new CourseServiceImpl();
    private TeacherServiceImpl teacherService=new TeacherServiceImpl();
    private CourseVideoServiceImpl courseVideoService=new CourseVideoServiceImpl();
    private HomeworkServiceImpl homeworkService=new HomeworkServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request,response);
    }
    //教师登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Teacher teacher = teacherService.login(username, password);
        String loginMsg="0";//0代表登录失败
        if(teacher!=null){
            loginMsg="1";//1登陆成功
            request.getSession().setAttribute("teacher",teacher);
        }
        response.getWriter().write(loginMsg);
    }

    //注销
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/onlinelearning/index.html");//重定向到home首页
    }

    //上传课程
    public void uploadCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Course course = new Course();
        for (FileItem fileItem : list) {
            if(fileItem.isFormField()){//如果是普通表单项
                String fieldName = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                switch (fieldName){//给course属性赋值
                    case "courseName":
                        course.setCourseName(value);
                        break;
                    case "courseInfo":
                        course.setCourseInfo(value);
                        break;
                    case "teacherType":
                        course.setTeacherType(value);
                        break;
                    case "isFree":
                        int i=value.equals("1")?1:0;
                        course.setIsFree(i);
                        break;
                    case "cost":
                        course.setCost(Double.valueOf(value));
                        break;
                }
            }else {
                System.out.println(fileItem.getName());
                File file = new File("E:\\file\\" + new Date().getTime() + fileItem.getName());
                fileItem.write(file);
                String path = file.getAbsolutePath();
                System.out.println("path="+path);
                course.setImgPath(path);
            }
        }
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        course.setDepartment(teacher.getDepartment());
        course.setSchool(teacher.getSchool());
        course.setTeacherId(teacher.getId());
        //添加课程
        boolean b = courseService.uploadCourse(course);
        String uploadMsg="0";//0代表上传失败
        if(b){
            uploadMsg="1";
        }
        System.out.println(uploadMsg.equals("1")?"上传成功":"上传失败");
//        String json = new Gson().toJson(uploadMsg);
        response.getWriter().write(uploadMsg);
    }

    //添加视频
    public void uploadVideo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CourseVideo courseVideo = new CourseVideo();
        String courseId = request.getParameter("courseId");
        for (FileItem fileItem : list) {
            if(fileItem.isFormField()){
                String fieldName = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                switch (fieldName){
                    case "videoName":
                        courseVideo.setVideoName(value);
                        break;
                    case "videoInfo":
                        courseVideo.setVideoInfo(value);
                        break;
                }
            }else {
                String fieldName = fileItem.getFieldName();
                File file = new File("E:\\file\\" + new Date().getTime() + fileItem.getName());
                fileItem.write(file);
                String path = file.getAbsolutePath();
                System.out.println("path="+path);
                switch (fieldName){
                    case "imgPath":
                        courseVideo.setImgPath(path);
                        break;
                    case "videoPath":
                        courseVideo.setVideoPath(path);
                        break;
                }
            }
        }
        boolean b = courseVideoService.addCourseVideo(courseVideo);
        String addVideoMsg=b?"1":"0";//0代表失败,1代表成功
        System.out.println(addVideoMsg);
    }


    //添加附件22
    public void uploadAttach(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fieldName="";
        for (FileItem fileItem : list) {

                 fieldName = fileItem.getFieldName();
                File file = new File("E:\\file\\" + new Date().getTime() + fileItem.getName());
                fileItem.write(file);
                String path = file.getAbsolutePath();
                System.out.println("path="+path);


        }

        Map<String,Object> map=new HashMap<>();
        Map<String ,String> data=new HashMap<>();
        data.put("data",fieldName);

        Gson gson = new Gson();
        map.put("code",0);
        map.put("msg","");
        map.put("data",data);
        String json=gson.toJson(map);
        System.out.println(json);
        response.getWriter().write(json);

    }

    //初始化教师课程界面
    public void init(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        List<Course> list = courseService.queryCourseByTeacherId(teacher.getId());
        Gson gson = new Gson();
        String json = gson.toJson(list);
        response.getWriter().write(json);
    }

    //发布作业
    public void addHomework(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Homework homework = new Homework();
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        for (FileItem fileItem : list) {
            if(fileItem.isFormField()){
                String fieldName = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                switch (fieldName){
                    case "courseName":
                        Course course = courseService.queryCourseByTeacherIdAndCourseName(value, teacher.getId());
                        homework.setCourseId(course.getId());
                        homework.setTeacherId(teacher.getId());
                        break;
                    case "title":
                        homework.setTitle(value);
                        break;
                    case "content":
                        homework.setContent(value);
                        break;
                    case "endTime":
                        homework.setEndTime(value);
                        break;

                }
            }else {
                File file = new File("E:\\file\\" + new Date().getTime() + fileItem.getName());
                fileItem.write(file);
                String path = file.getAbsolutePath();
                homework.setFile(path);
            }
        }
        boolean b = homeworkService.addHomework(homework);
        String homeworkMsg=b==true?"1":"0";
        System.out.println(homeworkMsg);
        response.getWriter().write(homeworkMsg);
    }

    //
    public void getTeacherCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        List<Course> list = courseService.queryCourseByTeacherId(teacher.getId());
        Gson gson = new Gson();
        String json = gson.toJson(list);
        response.getWriter().write(json);
    }
}
