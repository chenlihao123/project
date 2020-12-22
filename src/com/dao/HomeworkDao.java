package com.dao;

import com.entity.Homework;
import com.entity.HomeworkInfo;
import com.entity.HomeworkManage;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-14 9:45
 */
public interface HomeworkDao {
    boolean addHomework(Homework homework);
    Homework queryHomeworkByCourseIdAndTitle(int id,String title);
    List<HomeworkInfo> queryHomeworkInfoByStudentId(int id);
    //统计某个教师某个作业的提交人数
    int getFinishTotalByHomeworkId(int id);
    //统计某个教师某个作业的已批阅的人数
    int getCorrectTotal(int id);
    //获取某个老师所有作业信息
    List<Homework> queryHomeworkByTeacherId(int id);
}
