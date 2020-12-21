package com.service;

import com.entity.CourseVideo;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-11 15:22
 */
public interface CourseVideoService {
    //添加视频
    boolean addCourseVideo(CourseVideo courseVideo);
    //根据课程id查找视频
    List<CourseVideo> queryVideoByCourseId(int id);
    //根据视频名称在课程列表里查询
    CourseVideo queryVideoFromList(String videoName,List<CourseVideo> list);
    //根据课程id和学生id查找视频
    List<CourseVideo> queryVideoByCourseIdAndStudentId(int courseId,int studentId);
    //根据视频id，更新学生完成情况
    boolean updateStudentVideoState(int id);
}
