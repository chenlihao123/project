package com.dao.impl;

import com.dao.BaseDao;
import com.dao.TeacherDao;
import com.entity.Teacher;

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
}
