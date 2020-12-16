package com.service.impl;

import com.dao.impl.CourseVideoDaoImpl;
import com.entity.CourseVideo;
import com.service.CourseVideoService;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-11 15:23
 */
public class CourseVideoServiceImpl implements CourseVideoService {
    CourseVideoDaoImpl courseVideoDao=new CourseVideoDaoImpl();
    @Override
    public boolean addCourseVideo(CourseVideo courseVideo) {
        return courseVideoDao.addVideo(courseVideo);
    }

    @Override
    public List<CourseVideo> queryVideoByCourseId(int id) {
        return courseVideoDao.queryVideoByCourseId(id);
    }

    @Override
    public CourseVideo queryVideoFromList(String videoName, List<CourseVideo> list) {
        for (CourseVideo video : list) {
            if(video.getVideoName().equals(videoName)){
                return video;
            }
        }
        return null;
    }

    @Override
    public List<CourseVideo> queryVideoByCourseIdAndStudentId(int courseId, int studentId) {
        return courseVideoDao.queryVideoByCourseIdAndStudentId(courseId,studentId);
    }
}
