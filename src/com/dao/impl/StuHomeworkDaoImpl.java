package com.dao.impl;

import com.dao.BaseDao;
import com.dao.StuHomeworkDao;
import com.entity.StuHomework;

import java.util.List;

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

    @Override
    public List<StuHomework> queryStuHomeworkByHomeworkId(int homeworkId) {
        String sql="select * from stuhomework where homeworkId = ?";
        return queryForList(StuHomework.class,sql,homeworkId);
    }

    @Override
    public StuHomework queryStuHomeworkById(int id) {
        String sql="select * from stuhomework where id = ?";
        return queryForOne(StuHomework.class,sql,id);
    }

    @Override
    public boolean updateStuHomeworkInfo(StuHomework stuHomework) {
        String sql="update stuhomework set score = ? where id = ?";
        return update(sql,stuHomework.getScore(),stuHomework.getId())>=0?true:false;
    }
}
