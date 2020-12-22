package com.service.impl;

import com.dao.impl.HomeworkDaoImpl;
import com.entity.Homework;
import com.entity.HomeworkInfo;
import com.entity.HomeworkManage;
import com.service.HomeworkService;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Homework queryHomeworkByCourseIdAndTitle(int id, String title) {
        return homeworkDao.queryHomeworkByCourseIdAndTitle(id,title);
    }

    @Override
    public List<HomeworkInfo> queryHomeworkInfoByStudentId(int id) {
        return homeworkDao.queryHomeworkInfoByStudentId(id);
    }

    @Override
    public List<HomeworkManage> getHomeworkManageByTeacherId(int id) {
        ArrayList<HomeworkManage> list = new ArrayList<>();
        List<Homework> homework = homeworkDao.queryHomeworkByTeacherId(id);
        for (Homework homework1 : homework) {
            HomeworkManage homeworkManage = new HomeworkManage();
            homeworkManage.setId(homework1.getId());
            homeworkManage.setEndTime(homework1.getEndTime());
            homeworkManage.setTotal(homeworkDao.getFinishTotalByHomeworkId(homework1.getId()));
            homeworkManage.setTitle(homework1.getTitle());
            String progress=homeworkDao.getCorrectTotal(homework1.getId())+"/"+homeworkDao.getFinishTotalByHomeworkId(homework1.getId());
            homeworkManage.setProgress(progress);
            list.add(homeworkManage);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new HomeworkServiceImpl().getHomeworkManageByTeacherId(1));
    }
}
