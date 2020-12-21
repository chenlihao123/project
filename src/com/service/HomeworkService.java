package com.service;

import com.entity.Homework;

/**
 * @author chenlihao
 * @create 2020-12-14 9:54
 */
public interface HomeworkService {
    //添加作业
    boolean addHomework(Homework homework);
    Homework queryHomeworkByCourseIdAndTitle(int id, String title);
}
