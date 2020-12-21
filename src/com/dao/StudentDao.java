package com.dao;

import com.entity.Student;

/**
 * @author chenlihao
 * @create 2020-12-09 15:20
 */
public interface StudentDao {
    //根据username password 查询学生
    Student queryByUserNameAndPassword(String username,String password);

    //添加学生用户
    boolean addStudent(Student student);

    //根据手机号查询学生
    Student queryStudentByPhone(String phone);
    //根据邮件查询学生
    Student queryStudentByEmail(String email);
    //根据用户名（学号）查询学生
    Student queryStudentByUsername(String username);
    //更新学生信息
    boolean updateStuInfo(Student student);
}
