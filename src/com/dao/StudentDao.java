package com.dao;

import com.entity.Student;

/**
 * @author chenlihao
 * @create 2020-12-09 15:20
 */
public interface StudentDao {
    //根据username password 查询学生
    Student queryByUserNameAndPassword(String username,String password);
}
