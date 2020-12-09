package com.service.impl;

import com.dao.impl.StudentDaoImpl;
import com.entity.Student;
import com.service.StudentService;

/**
 * @author chenlihao
 * @create 2020-12-09 15:35
 */
public class StudentServiceImpl implements StudentService {
    private StudentDaoImpl studentDao=new StudentDaoImpl();

    /**
     * 学生登录
     * @param username 用户名
     * @param password 密码
     * @return Student
     */
    @Override
    public Student login(String username, String password) {
        return studentDao.queryByUserNameAndPassword(username,password);
    }
}
