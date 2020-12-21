package com.service.impl;

import com.dao.impl.StuHomeworkDaoImpl;
import com.entity.StuHomework;
import com.service.StuHomeworkService;

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
}
