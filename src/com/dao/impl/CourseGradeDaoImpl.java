package com.dao.impl;

import com.dao.BaseDao;
import com.dao.CourseGradeDao;
import com.entity.CourseGrade;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-17 15:18
 */
public class CourseGradeDaoImpl extends BaseDao implements CourseGradeDao {
    @Override
    public List<CourseGrade> queryCourseGradeByCourseId(int courseId) {
        String sql="select * from courseGrade where courseId = ?";
        return queryForList(CourseGrade.class,sql,courseId);
    }
}
