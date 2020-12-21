package com.service;

import com.entity.Homework;
import com.entity.StuHomework;

/**
 * @author chenlihao
 * @create 2020-12-20 16:54
 */
public interface StuHomeworkService {
    //添加学生任务
    boolean addStuHomework(StuHomework stuHomework);

}
