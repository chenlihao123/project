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
        String sql="select * from coursegrade where courseId = ?";
        return queryForList(CourseGrade.class,sql,courseId);
    }

    @Override
    public CourseGrade queryCourseGradeByCourseIdAndStudentId(int courseId, int stundentId) {
        String sql="select * from coursegrade where courseId=? and studentId = ?";
        return queryForOne(CourseGrade.class,sql,courseId,stundentId);
    }

    @Override
    public boolean addCourseGrade(CourseGrade c) {
        String sql="insert into coursegrade (studentId,studentName,studentImg,courseId,courseName,content,grade,createTime) values (?,?,?,?,?,?,?,?)";
        int i = update(sql, c.getStudentId(), c.getStudentName(), c.getStudentImg(), c.getCourseId(), c.getCourseName(), c.getContent(), c.getGrade(), c.getCreateTime());
        return i>=0?true:false;
    }
}
