package com.servlet;

import com.entity.*;
import com.google.gson.Gson;
import com.service.impl.*;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

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
    private StudentServiceImpl studentService=new StudentServiceImpl();
    private StuCourseServiceImpl stuCourseService=new StuCourseServiceImpl();
    private StuHomeworkServiceImpl stuHomeworkService=new StuHomeworkServiceImpl();
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
                System.out.println("filename:"+fileItem.getName());
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
        course.setTeacherName(teacher.getRealName());
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
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
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
                    case "courseName":
                        Course course = courseService.queryCourseByTeacherIdAndCourseName(value, teacher.getId());
                        courseVideo.setCourseId(course.getId());
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
        if(b){
            addHomeworkToStu(homeworkService.queryHomeworkByCourseIdAndTitle(homework.getCourseId(),homework.getTitle()));
        }
        String homeworkMsg=b==true?"1":"0";

        System.out.println(homeworkMsg);
        response.getWriter().write(homeworkMsg);
    }

    //将作业分布到每个学生
    private void addHomeworkToStu(Homework homework){
        List<StuCourse> stuCourses = stuCourseService.queryStuCourseByCourseId(homework.getCourseId());
        StuHomework stuHomework = new StuHomework();
        for (StuCourse stuCourse : stuCourses) {
            stuHomework.setStudentId(stuCourse.getStudentId());
            stuHomework.setHomeworkId(homework.getId());
            stuHomework.setCourseId(homework.getCourseId());
            stuHomeworkService.addStuHomework(stuHomework);
        }
    }

    //回填老师课程信息
    public void getTeacherCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        List<Course> list = courseService.queryCourseByTeacherId(teacher.getId());
        Gson gson = new Gson();
        String json = gson.toJson(list);
        response.getWriter().write(json);
    }

    //回填课程视频信息
    public void getCourseVideo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String courseName = request.getParameter("courseName");//获取课程名称
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        Course course = courseService.queryCourseByTeacherIdAndCourseName(courseName, teacher.getId());
        List<CourseVideo> courseVideos = courseVideoService.queryVideoByCourseId(course.getId());
        String videoName = request.getParameter("videoName");
        Gson gson = new Gson();
        if(videoName!=null&&!"".equals(videoName)){//如果有传回来videoName且不是空字符串
            CourseVideo courseVideo = courseVideoService.queryVideoFromList(videoName, courseVideos);//获取课程
            courseVideos=new ArrayList<>();
            courseVideos.add(courseVideo);
        }
        String json = gson.toJson(courseVideos);
        response.getWriter().write(json);
    }
}
