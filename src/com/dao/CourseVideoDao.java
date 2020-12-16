package com.dao;

import com.entity.CourseVideo;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-11 15:17
 */
public interface CourseVideoDao {
    //添加视频
    boolean addVideo(CourseVideo courseVideo);
    //根据课程id查找视频
    List<CourseVideo> queryVideoByCourseId(int id);
    //根据课程id和学生id查找视频
    List<CourseVideo> queryVideoByCourseIdAndStudentId(int courseId,int studentId);
}
