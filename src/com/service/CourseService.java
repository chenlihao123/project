package com.service;

import com.entity.Course;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-11 10:43
 */
public interface CourseService {
    //上传课程
    boolean uploadCourse(Course course);
    //根据教师id查询课程
    List<Course> queryCourseByTeacherId(int id);
    //根据教师id和课程名
    Course queryCourseByTeacherIdAndCourseName(String courseName,int id);
}
