package com.dao;

import com.entity.Teacher;

/**
 * @author chenlihao
 * @create 2020-12-11 9:14
 */
public interface TeacherDao {
    //根据用户名（教职工号）和密码查询老师
    Teacher queryTeacherByUsernameAndPassword(String username,String password);
}
