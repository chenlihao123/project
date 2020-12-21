package com.service;

import com.entity.Course;
import com.entity.StuCourse;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-16 10:17
 */
public interface StuCourseService {
    //根据学生id 查询学生课程信息列表
    List<Course> queryCourseByStudentId(int id);
    //学生添加课程
    boolean addCourse(StuCourse stuCourse);
    //根据课程id查询StuCourse
    List<StuCourse> queryStuCourseByCourseId(int id);
}
