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

    @Override
    public boolean addCourse(StuCourse stuCourse) {
        String sql="insert into stucourse (studentId,courseId,totalScore,homeworkScore,videoScore,videoProgress,isPass) values (?,?,?,?,?,?,?)";
        int i = update(sql, stuCourse.getStudentId(),stuCourse.getCourseId(), stuCourse.getTotalScore(), stuCourse.getHomeworkScore(), stuCourse.getVideoScore(), stuCourse.getVideoProgress(), stuCourse.getIsPass());
        return i>=0?true:false;
    }

    @Override
    public List<StuCourse> queryStuCourseByCourseId(int id) {
        String sql ="select * from stucourse where courseId =?";
        return queryForList(StuCourse.class,sql,id);
    }
}
