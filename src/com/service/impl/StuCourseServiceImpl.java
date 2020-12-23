package com.service.impl;

import com.dao.impl.*;
import com.entity.*;
import com.service.StuCourseService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-16 10:22
 */
public class StuCourseServiceImpl implements StuCourseService {
    private StuCourseDaoImpl stuCourseDao=new StuCourseDaoImpl();
    private CourseDaoImpl courseDao=new CourseDaoImpl();
    private StuHomeworkDaoImpl stuHomeworkDao=new StuHomeworkDaoImpl();
    private CourseVideoDaoImpl courseVideoDao=new CourseVideoDaoImpl();
    @Override
    public List<Course> queryCourseByStudentId(int id) {
        List<StuCourse> list = stuCourseDao.queryStuCourseByStudentId(id);
        ArrayList<Course> list1 = new ArrayList<>();
        for (StuCourse stuCourse : list) {
            Course course = courseDao.queryCourseById(stuCourse.getCourseId());
            list1.add(course);
        }
        return list1;
    }

    @Override
    public boolean addCourse(StuCourse stuCourse) {
        return stuCourseDao.addCourse(stuCourse);
    }

    @Override
    public List<StuCourse> queryStuCourseByCourseId(int id) {
        return stuCourseDao.queryStuCourseByCourseId(id);
    }

    @Override
    public StuCourseScore getStuCourseScoreByCourseIdAndStudentId(int courseId, int studentId) {
        StuCourseScore score = new StuCourseScore();
        //获取作业成绩
        List<StuHomework> stuHomeworks = stuHomeworkDao.queryStuHomeworkByCourseIdAndStudentId(courseId, studentId);
        double homeworkScore=0;
        for (StuHomework stuHomework : stuHomeworks) {
            if(stuHomework.getScore()!=null&&stuHomework.getScore()!=0){
                homeworkScore+=stuHomework.getScore();
            }
        }
        homeworkScore/=stuHomeworks.size();
        homeworkScore*=0.6;
        score.setHomeworkScore(homeworkScore);
        //获取视频成绩
        List<CourseVideo> courseVideos = courseVideoDao.queryVideoByCourseIdAndStudentId(courseId, studentId);
        double videoScore=0;
        for (CourseVideo courseVideo : courseVideos) {
            if(courseVideo.getIsFinish()==1){
                videoScore++;
            }
        }
        videoScore/=courseVideos.size();
        videoScore=videoScore*100*0.4;
        score.setVideoScore(videoScore);
        //统计总成绩
        score.setCourseScore(videoScore+homeworkScore);
        return score;
    }

//    public static void main(String[] args) {
//        System.out.println(new StuCourseServiceImpl().getStuCourseScoreByCourseIdAndStudentId(1, 1));
//    }
}
