package com.servlet;

import com.entity.*;
import com.google.gson.Gson;
import com.service.impl.*;
import com.utils.FileUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-09 15:37
 */
@WebServlet("/stu.do")
public class StudentServlet extends BaseServlet {
    private StudentServiceImpl studentService = new StudentServiceImpl();
    private CourseVideoServiceImpl courseVideoService = new CourseVideoServiceImpl();
    private StuCourseServiceImpl stuCourseService = new StuCourseServiceImpl();
    private CourseServiceImpl courseService = new CourseServiceImpl();
    private CourseGradeServiceImpl courseGradeService = new CourseGradeServiceImpl();
    private HomeworkServiceImpl homeworkService = new HomeworkServiceImpl();

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
        request.getSession().setAttribute("phone", phone);
        request.getSession().setAttribute("sendSMS", sendSMS);//将验证码放到session域当中
    }

    //唯一性校验
    public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        Student student = null;
        String checkMsg = "1";//1代表检查合格
        if (phone != null) {
            student = studentService.queryStudentByPhone(phone);
        }
        if (email != null) {
            student = studentService.queryStudentByEmail(email);
        }
        if (username != null) {
            student = studentService.queryStudentByUsername(username);
        }
        if (student != null) {
            checkMsg = "0";//如果student不为空，说明用户有重复，0代表不合格
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
        map.put("courseMsg", course);
        map.put("courseGradeMsg", courseGrades);
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

    //添加课程
    public void addCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StuCourse stuCourse = new StuCourse();
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        Course course = courseService.queryCourseById(courseId);
        Student student = (Student) request.getSession().getAttribute("student");
        stuCourse.setCourseId(courseId);
        stuCourse.setStudentId(student.getId());
        stuCourseService.addCourse(stuCourse);
        List<CourseVideo> courseVideos = courseVideoService.queryVideoByCourseId(courseId);
        CourseVideo courseVideo = new CourseVideo();
        courseVideo.setStudentId(student.getId());
        boolean b = false;
        for (CourseVideo courseVideo1 : courseVideos) {
            courseVideo.setCourseId(courseId);
            courseVideo.setVideoName(courseVideo1.getVideoName());
            courseVideo.setImgPath(courseVideo1.getImgPath());
            courseVideo.setVideoPath(courseVideo1.getVideoPath());
            courseVideo.setVideoInfo(courseVideo1.getVideoInfo());
            b = courseVideoService.addCourseVideo(courseVideo);
        }
        String addMsg = b ? "1" : "0";
        Gson gson = new Gson();
        String json = gson.toJson(addMsg);
        response.getWriter().write(json);

    }

    //更新视频完成情况
    public void updateVideoFinish(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer videoId = Integer.valueOf(request.getParameter("videoId"));
        System.out.println(videoId);
        boolean b = courseVideoService.updateStudentVideoState(videoId);
        String updateMsg = b ? "1" : "0";
        String json = new Gson().toJson(updateMsg);
        response.getWriter().write(json);
    }

    //回填学生个人信息
    public void getStuInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Student student = (Student) request.getSession().getAttribute("student");
        String json = new Gson().toJson(student);
        response.getWriter().write(json);
    }

    //更新基础资料
    public void updateBaseInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Student student = (Student) request.getSession().getAttribute("student");
        String newName = request.getParameter("newName");
        String newSchool = request.getParameter("newSchool");
        String gender = request.getParameter("gender");
        student.setSchool(newSchool);
        student.setGender(gender);
        student.setRealName(newName);
        boolean b = studentService.updateStuInfo(student);
        String updateMsg = b ? "1" : "0";
        response.getWriter().write(updateMsg);
    }

    //更新用户资料
    public void updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newUsername = request.getParameter("newUsername");
        String newPhone = request.getParameter("newPhone");
        String newEmail = request.getParameter("newEmail");
        String newPassword = request.getParameter("newPassword");
        Student student = (Student) request.getSession().getAttribute("student");
        student.setUsername(newUsername);
        student.setPhone(newPhone);
        student.setEmail(newEmail);
        student.setPassword(newPassword);
        boolean b = studentService.updateStuInfo(student);
        String updateMsg = b ? "1" : "0";
        response.getWriter().write(updateMsg);
    }

    //更新头像
    public void updateImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Student student = (Student) request.getSession().getAttribute("student");
        for (FileItem fileItem : list) {
            if (!fileItem.isFormField()) {
                File file = new File("E:\\file\\" + new Date().getTime() + fileItem.getName());
                fileItem.write(file);
                String path = file.getAbsolutePath();
                student.setImgPath(path);
            }
        }
        boolean b = studentService.updateStuInfo(student);
        String updateMsg = b ? "1" : "0";
        response.getWriter().write(updateMsg);
    }

    //回填作业信息
    public void getHomeworkInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Student student = (Student) request.getSession().getAttribute("student");
        List<HomeworkInfo> homeworkInfos = homeworkService.queryHomeworkInfoByStudentId(student.getId());
        response.getWriter().write(new Gson().toJson(homeworkInfos));
    }

    //下载作业
    public void downloadHomework(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1、获取要下载的文件名
        String downloadFileName =request.getParameter("downloadFileName");
        //downloadFileName=downloadFileName.substring(3);
        // 2、读取要下载的文件内容 (通过 ServletContext 对象可以读取)
        ServletContext servletContext = getServletContext();
        // 获取要下载的文件类型
        String mimeType = servletContext.getMimeType(downloadFileName);
        FileUtils.singleDownload(request,response,"E:\\file\\"+downloadFileName,downloadFileName,mimeType);
//        String mimeType = servletContext.getMimeType( downloadFileName);
//        // 4、在回传前，通过响应头告诉客户端返回的数据类型
//        response.setContentType(mimeType);
//        // 5、还要告诉客户端收到的数据是用于下载使用（还是使用响应头）
//        // Content-Disposition 响应头，表示收到的数据怎么处理
//        // attachment 表示附件，表示下载使用
//        // filename= 表示指定下载的文件名
//        response.setHeader("Content-Disposition", "attachment; filename=" + downloadFileName);
//        InputStream resourceAsStream = servletContext.getResourceAsStream("file/" +
//        downloadFileName);
////        InputStream resourceAsStream = servletContext.getResourceAsStream(downloadFileName);
//        // 获取响应的输出流
//        OutputStream outputStream = response.getOutputStream();
//        // 3、把下载的文件内容回传给客户端
//        // 读取输入流中全部的数据，复制给输出流，输出给客户端
//        IOUtils.copy(resourceAsStream, outputStream);
    }
}


