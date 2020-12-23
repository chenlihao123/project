package com.service;

import com.entity.Teacher;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-11 9:19
 */
public interface TeacherService {
    //登录
    Teacher login(String username,String password);
    List<Teacher> queryAllTeacher();

    int deleteTeacherById(int id);

    int addTeacher(String name, String phone, String school, String department, String sex, String psd);
}
