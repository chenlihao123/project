package com.service;

import com.entity.Homework;
import com.entity.HomeworkInfo;
import com.entity.HomeworkManage;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-14 9:54
 */
public interface HomeworkService {
    //添加作业
    boolean addHomework(Homework homework);
    Homework queryHomeworkByCourseIdAndTitle(int id, String title);
    List<HomeworkInfo> queryHomeworkInfoByStudentId(int id);
    List<HomeworkManage> getHomeworkManageByTeacherId(int id);
}
