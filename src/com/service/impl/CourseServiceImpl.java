package com.service.impl;

import com.dao.impl.CourseDaoImpl;
import com.entity.Course;
import com.service.CourseService;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-11 10:44
 */
public class CourseServiceImpl implements CourseService {
    private CourseDaoImpl courseDao=new CourseDaoImpl();
    @Override
    public boolean uploadCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public List<Course> queryCourseByTeacherId(int id) {
        return courseDao.queryCourseByTeacherId(id);
    }
}
