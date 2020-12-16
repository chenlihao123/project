package com.dao.impl;

import com.dao.BaseDao;
import com.dao.StuCourseDao;
import com.entity.StuCourse;
import com.entity.Student;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-16 10:13
 */
public class StuCourseDaoImpl extends BaseDao implements StuCourseDao {

    @Override
    public List<StuCourse> queryStuCourseByStudentId(int id) {
        String sql ="select * from stucourse where studentId =?";
        return queryForList(StuCourse.class,sql,id);
    }
}
