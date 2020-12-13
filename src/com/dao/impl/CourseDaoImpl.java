package com.dao.impl;

import com.dao.BaseDao;
import com.dao.CourseDao;
import com.entity.Course;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-11 10:46
 */
public class CourseDaoImpl extends BaseDao implements CourseDao {
    @Override
    public boolean addCourse(Course course) {
        String sql="insert into course (courseName,teacherId,department,school,isFree,imgPath,courseInfo,cost,type,teacherType) values (?,?,?,?,?,?,?,?,?,?)";
        int b = update(sql, course.getCourseName(), course.getTeacherId(), course.getDepartment(), course.getSchool(), course.getIsFree(), course.getImgPath(), course.getCourseInfo(),course.getCost(),course.getType(),course.getTeacherType());
        return b>=0?true:false;
    }

    @Override
    public List<Course> queryCourseByTeacherId(int id) {
        String sql="select * from course where teacherId = ?";
        return queryForList(Course.class, sql, id);
    }
}
