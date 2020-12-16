package com.dao;

import com.entity.Course;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-11 10:45
 */
public interface CourseDao {
    //添加课程
    boolean addCourse(Course course);
    //根据教师id查询课程列表
    List<Course> queryCourseByTeacherId(int id);
    //根据教师id和课程名查找课程
    Course queryCourseByTeacherIdAndCourseName(String courseName,int teacherId);
    //根据id查找课程
    Course queryCourseById(int id);
}
