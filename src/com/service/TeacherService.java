package com.service;

import com.entity.Teacher;

/**
 * @author chenlihao
 * @create 2020-12-11 9:19
 */
public interface TeacherService {
    //登录
    Teacher login(String username,String password);
}
