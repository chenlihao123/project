package com.dao.impl;

import com.dao.BaseDao;
import com.dao.CourseVideoDao;
import com.entity.CourseVideo;

/**
 * @author chenlihao
 * @create 2020-12-11 15:17
 */
public class CourseVideoDaoImpl extends BaseDao implements CourseVideoDao {
    @Override
    public boolean addVideo(CourseVideo video) {
        String sql="insert into coursevideo (studentId,courseId,videoName,imgPath,videoPath,videoInfo,isFinish) values (?,?,?,?,?,?,?)";
        int i = update(sql, video.getStudentId(), video.getCourseId(), video.getVideoName(), video.getImgPath(), video.getVideoPath(), video.getVideoInfo(), video.getIsFinish());
        return i>=0?true:false;
    }
}
