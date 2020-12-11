package com.service.impl;

import com.dao.impl.CourseVideoDaoImpl;
import com.entity.CourseVideo;
import com.service.CourseVideoService;

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
}
