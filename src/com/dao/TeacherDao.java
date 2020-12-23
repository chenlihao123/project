package com.dao;

import com.entity.Teacher;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-11 9:14
 */
public interface TeacherDao {
    //根据用户名（教职工号）和密码查询老师
    Teacher queryTeacherByUsernameAndPassword(String username,String password);

    List<Teacher> queryAllTeacher();

    int deleteTeacherById(int id);

    int addTeacher(String name, String phone, String school, String department, String sex, String psd);
}
