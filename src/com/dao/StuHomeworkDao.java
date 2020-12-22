package com.dao;

import com.entity.StuHomework;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-20 16:49
 */
public interface StuHomeworkDao {
    //添加学生任务
    boolean addStuHomework(StuHomework stuHomework);
    //根据homeworkId查找学生作业
    List<StuHomework> queryStuHomeworkByHomeworkId(int homeworkId);
    //根据id查找StuHomework
    StuHomework queryStuHomeworkById(int id);
    //更新学生作业状态
    boolean updateStuHomeworkInfo(StuHomework stuHomework);
}
