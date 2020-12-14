package com.dao.impl;

import com.dao.BaseDao;
import com.dao.HomeworkDao;
import com.entity.Homework;

/**
 * @author chenlihao
 * @create 2020-12-14 9:47
 */
public class HomeworkDaoImpl extends BaseDao implements HomeworkDao {
    @Override
    public boolean addHomework(Homework homework) {
        System.out.println(homework);
        String sql="insert into homework (teacherId,courseId,title,content,file,endTime) values (?,?,?,?,?,?)";
        int i = update(sql, homework.getTeacherId(), homework.getCourseId(), homework.getTitle(), homework.getContent(), homework.getFile(), homework.getEndTime());
        return i>=0?true:false;
    }
}
