package com.dao.impl;

import com.dao.BaseDao;
import com.dao.StuHomeworkDao;
import com.entity.StuHomework;

/**
 * @author chenlihao
 * @create 2020-12-20 16:50
 */
public class StuHomeworkDaoImpl extends BaseDao implements StuHomeworkDao {
    @Override
    public boolean addStuHomework(StuHomework stuHomework) {
        String sql="insert into stuhomework (studentId,homeworkId,courseId,content,file,createTime,score) values (?,?,?,?,?,?,?)";
        int i = update(sql, stuHomework.getStudentId(), stuHomework.getHomeworkId(),stuHomework.getCourseId(), stuHomework.getContent(), stuHomework.getFile(), stuHomework.getCreateTime(), stuHomework.getScore());
        return i>=0?true:false;
    }
}
