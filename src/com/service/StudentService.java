package com.service;

import com.entity.Student;

/**
 * @author chenlihao
 * @create 2020-12-09 15:31
 */
public interface StudentService {
    Student login(String username,String password);
}
