package com.service;

import com.entity.CourseGrade;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-17 15:31
 */
public interface CourseGradeService {
    //根据课程id查询课程评价信息
    List<CourseGrade> queryCourseGradeByCourseId(int courseId);
}
