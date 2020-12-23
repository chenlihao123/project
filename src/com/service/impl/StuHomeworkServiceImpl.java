package com.service.impl;

import com.dao.impl.StuHomeworkDaoImpl;
import com.entity.StuHomework;
import com.service.StuHomeworkService;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-20 16:54
 */
public class StuHomeworkServiceImpl implements StuHomeworkService {
    private StuHomeworkDaoImpl stuHomeworkDao=new StuHomeworkDaoImpl();
    @Override
    public boolean addStuHomework(StuHomework stuHomework) {
        return stuHomeworkDao.addStuHomework(stuHomework);
    }

    @Override
    public List<StuHomework> queryStuHomeworkByHomeworkId(int homeworkId) {
        return stuHomeworkDao.queryStuHomeworkByHomeworkId(homeworkId);
    }

    @Override
    public StuHomework queryStuHomeworkById(int id) {
        return stuHomeworkDao.queryStuHomeworkById(id);
    }

    @Override
    public boolean updateStuHomeworkInfo(StuHomework stuHomework) {
        return stuHomeworkDao.updateStuHomeworkInfo(stuHomework);
    }

    @Override
    public StuHomework queryStuHomeworkByStudentIdAndHomeworkId(int studentId, int homeworkId) {
        return stuHomeworkDao.queryStuHomeworkByStudentIdAndHomeworkId(studentId,homeworkId);
    }
}
