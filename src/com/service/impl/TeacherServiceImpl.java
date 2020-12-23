package com.service.impl;

import com.dao.impl.TeacherDaoImpl;
import com.entity.Teacher;
import com.service.TeacherService;

import java.util.List;

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
    @Override
    public List<Teacher> queryAllTeacher() {
        return teacherDao.queryAllTeacher();
    }

    @Override
    public int deleteTeacherById(int id) {
        return teacherDao.deleteTeacherById(id);
    }

    @Override
    public int addTeacher(String name, String phone, String school, String department, String sex, String psd) {
        return teacherDao.addTeacher(name, phone, school, department, sex, psd);
    }
}
