package com.service.impl;

import com.dao.impl.TeacherDaoImpl;
import com.entity.Teacher;
import com.service.TeacherService;

/**
 * @author chenlihao
 * @create 2020-12-11 9:19
 */
public class TeacherServiceImpl implements TeacherService {
    private TeacherDaoImpl teacherDao =new TeacherDaoImpl();
    @Override
    public Teacher login(String username, String password) {
        return teacherDao.queryTeacherByUsernameAndPassword(username,password);
    }
}
