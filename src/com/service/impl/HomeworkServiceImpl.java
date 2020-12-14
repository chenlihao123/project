package com.service.impl;

import com.dao.impl.HomeworkDaoImpl;
import com.entity.Homework;
import com.service.HomeworkService;

/**
 * @author chenlihao
 * @create 2020-12-14 9:57
 */
public class HomeworkServiceImpl implements HomeworkService {
    private HomeworkDaoImpl homeworkDao=new HomeworkDaoImpl();
    @Override
    public boolean addHomework(Homework homework) {
        return homeworkDao.addHomework(homework);
    }
}
