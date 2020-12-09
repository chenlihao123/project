package com.dao.impl;

import com.dao.BaseDao;
import com.dao.StudentDao;
import com.entity.Student;

/**
 * @author chenlihao
 * @create 2020-12-09 15:21
 */
public class StudentDaoImpl extends BaseDao implements StudentDao {

    @Override
    public Student queryByUserNameAndPassword(String username, String password) {
        String sql="select * from student where username = ? and password = ?";
        return queryForOne(Student.class,sql,username,password);
    }
//    测试sql语句是否正确
//    public static void main(String[] args) {
//        System.out.println(new StudentDaoImpl().login("123456", "123456"));
//    }
}
