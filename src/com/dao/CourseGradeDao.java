package com.dao;

import com.entity.CourseGrade;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-17 15:17
 */
public interface CourseGradeDao {
    //根据课程id查询课程评分信息
    List<CourseGrade> queryCourseGradeByCourseId(int courseId);


}
