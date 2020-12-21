package com.dao;

import com.entity.StuCourse;
import com.entity.Student;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-16 10:12
 */
public interface StuCourseDao {
    //根据学生id查询课程
    List<StuCourse> queryStuCourseByStudentId(int id);
    //添加课程
    boolean addCourse(StuCourse stuCourse);
    //根据课程id查询StuCourse
    List<StuCourse> queryStuCourseByCourseId(int id);
}
