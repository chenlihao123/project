package com.service;

import com.entity.Student;

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
}
