package com.dao.impl;

import com.dao.BaseDao;
import com.dao.CourseVideoDao;
import com.entity.CourseVideo;

import java.util.List;

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

    @Override
    public List<CourseVideo> queryVideoByCourseId(int id) {
        String sql="select * from coursevideo where courseId = ? and ISNULL(studentId)";
        return queryForList(CourseVideo.class,sql,id);
    }

    @Override
    public List<CourseVideo> queryVideoByCourseIdAndStudentId(int courseId, int studentId) {
        String sql="select * from coursevideo where courseId=? and studentId=?";
        return queryForList(CourseVideo.class,sql,courseId,studentId);
    }

    @Override
    public boolean updateStudentVideoState(int id) {
        String sql="update coursevideo set isFinish = 1 where id =?";
        return update(sql,id)>=0?true:false;
    }


}
