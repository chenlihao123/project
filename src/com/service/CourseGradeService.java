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
    //根据学生id和课程id查询课程评分信息
    CourseGrade queryCourseGradeByCourseIdAndStudentId(int courseId,int stundentId);;
    //添加课程评价
    boolean addCourseGrade(CourseGrade courseGrade);
}
