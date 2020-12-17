package com.service.impl;

import com.dao.impl.CourseGradeDaoImpl;
import com.entity.CourseGrade;
import com.service.CourseGradeService;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-17 15:32
 */
public class CourseGradeServiceImpl implements CourseGradeService {
    CourseGradeDaoImpl courseGradeDao=new CourseGradeDaoImpl();
    @Override
    public List<CourseGrade> queryCourseGradeByCourseId(int courseId) {
        return courseGradeDao.queryCourseGradeByCourseId(courseId);
    }
}
