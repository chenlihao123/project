package com.dao;

import com.entity.StuCourse;
import com.entity.Student;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-16 10:12
 */
public interface StuCourseDao {
    List<StuCourse> queryStuCourseByStudentId(int id);
}
