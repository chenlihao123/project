package com.service.impl;

import com.dao.impl.CourseDaoImpl;
import com.dao.impl.StuCourseDaoImpl;
import com.dao.impl.StudentDaoImpl;
import com.entity.Course;
import com.entity.StuCourse;
import com.service.StuCourseService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-16 10:22
 */
public class StuCourseServiceImpl implements StuCourseService {
    private StuCourseDaoImpl stuCourseDao=new StuCourseDaoImpl();
    private CourseDaoImpl courseDao=new CourseDaoImpl();
    @Override
    public List<Course> queryCourseByStudentId(int id) {
        List<StuCourse> list = stuCourseDao.queryStuCourseByStudentId(id);
        ArrayList<Course> list1 = new ArrayList<>();
        for (StuCourse stuCourse : list) {
            Course course = courseDao.queryCourseById(stuCourse.getCourseId());
            list1.add(course);
        }
        return list1;
    }

    @Override
    public boolean addCourse(StuCourse stuCourse) {
        return stuCourseDao.addCourse(stuCourse);
    }

    @Override
    public List<StuCourse> queryStuCourseByCourseId(int id) {
        return stuCourseDao.queryStuCourseByCourseId(id);
    }
}
