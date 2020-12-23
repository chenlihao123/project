package com.service;

import com.entity.Notice;
import com.entity.Student;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-09 15:31
 */
public interface StudentService {
    /**
     * 学生登录
     * @param username
     * @param password
     * @return
     */
    Student login(String username,String password);

    /**
     * 注册
     * @param student
     * @return
     */
    boolean register(Student student);

    /**
     * 发送邮件
     * @param phone
     * @return
     */
    String sendSMS(String phone);
    //根据学生email，username，phone 分别查询学生
    Student queryStudentByEmail(String email);
    Student queryStudentByUsername(String username);
    Student queryStudentByPhone(String phone);

    List<Student> queryAllStudent();

    int deleteStudentById(int id);

    //更新学生信息
    boolean updateStuInfo(Student student);
    //通过id查找学生
    Student queryStudentById(int id);
    //发布通知
    boolean addNoticeToStudentByAdmin(int studentId,String title,String content,String createTime);
    //根据学生id获取通知
    List<Notice> queryNoticeByStudentId(int studentId);
    List<Student> queryStudent(String id, String name, String school, String sex);
}
