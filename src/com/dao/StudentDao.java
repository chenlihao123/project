package com.dao;

import com.entity.Student;

import java.util.List;

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
    //返回所以用户信息
    List<Student> queryAllStudent();
    //通过id删除学生
    int delectStudentById(int id);
    //更新学生信息
    boolean updateStuInfo(Student student);
    //通过id查找学生
    Student queryStudentById(int id);
}
