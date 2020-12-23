package com.dao.impl;

import com.dao.BaseDao;
import com.dao.TeacherDao;
import com.entity.Teacher;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-11 9:17
 */
public class TeacherDaoImpl extends BaseDao implements TeacherDao {

    @Override
    public Teacher queryTeacherByUsernameAndPassword(String username, String password) {
        String sql="select * from teacher where username=? and password = ?";
        return queryForOne(Teacher.class,sql,username,password);
    }
    @Override
    public List<Teacher> queryAllTeacher() {
        String sql = "SELECT * FROM `teacher`";
        return queryForList(Teacher.class,sql);
    }

    @Override
    public int deleteTeacherById(int id) {
        String sql = "DELETE FROM `teacher` WHERE id = ?";
        return update(sql,id);
    }

    @Override
    public int addTeacher(String name, String phone, String school, String department, String sex, String psd) {
        String sql = "INSERT INTO `teacher` (username, gender,phone,school,department,password) VALUES (?,?,?,?,?,?)";
        return update(sql, name, sex, phone, school, department, psd);
    }
}
