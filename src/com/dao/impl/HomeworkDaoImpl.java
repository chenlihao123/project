package com.dao.impl;

import com.dao.BaseDao;
import com.dao.HomeworkDao;
import com.entity.Homework;
import com.entity.HomeworkInfo;
import com.entity.HomeworkManage;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-14 9:47
 */
public class HomeworkDaoImpl extends BaseDao implements HomeworkDao {
    @Override
    public boolean addHomework(Homework homework) {
        String sql="insert into homework (teacherId,courseId,title,content,file,endTime) values (?,?,?,?,?,?)";
        int i = update(sql, homework.getTeacherId(), homework.getCourseId(), homework.getTitle(), homework.getContent(), homework.getFile(), homework.getEndTime());
        return i>=0?true:false;
    }

    @Override
    public Homework queryHomeworkByCourseIdAndTitle(int id, String title) {
        String sql = "select * from homework where courseId=? and title =?";
        return queryForOne(Homework.class,sql,id,title);

    }

    @Override
    public List<HomeworkInfo> queryHomeworkInfoByStudentId(int id) {
        String sql="select h.*,c.courseName,t.realName as teacherName from teacher t join( course c JOIN (stuhomework s JOIN homework h on s.homeworkId=h.id ) ON c.id=h.courseId)on t.id=h.teacherId where s.studentId = ?";
        return queryForList(HomeworkInfo.class,sql,id);
    }

    @Override
    public int getFinishTotalByHomeworkId(int id) {
        String sql="select count(1) from stuhomework where homeworkId=?";
        Number i = (Number) queryForSingleValue(sql, id);
        return i.intValue();
    }

    @Override
    public int getCorrectTotal(int id) {
        String sql="select count(1) from stuhomework where homeworkId=? and score is not null";
        Number i = (Number) queryForSingleValue(sql, id);
        return i.intValue();
    }

    @Override
    public List<Homework> queryHomeworkByTeacherId(int id) {
        String sql="select * from homework where teacherId = ?";
        return queryForList(Homework.class,sql,id);
    }

}
