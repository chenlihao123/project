package com.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.dao.impl.StudentDaoImpl;
import com.entity.Notice;
import com.entity.Student;
import com.service.StudentService;
import com.utils.SmsUtil;

import java.util.List;
import java.util.Random;

/**
 * @author chenlihao
 * @create 2020-12-09 15:35
 */
public class StudentServiceImpl implements StudentService {
    private StudentDaoImpl studentDao=new StudentDaoImpl();

    /**
     * 学生登录
     * @param username 用户名
     * @param password 密码
     * @return Student
     */
    @Override
    public Student login(String username, String password) {
        return studentDao.queryByUserNameAndPassword(username,password);
    }

    @Override
    public boolean register(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public String sendSMS(String phone) {
        Random random = new Random();
        String randomStr="";
        for (int i = 0; i < 6; i++) {
            randomStr+=random.nextInt(9);
        }
        try {
            SmsUtil.sendSms(phone,randomStr);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return randomStr;
    }

    @Override
    public Student queryStudentByEmail(String email) {
        return studentDao.queryStudentByEmail(email);
    }

    @Override
    public Student queryStudentByUsername(String username) {
        return studentDao.queryStudentByUsername(username);
    }

    @Override
    public Student queryStudentByPhone(String phone) {
        return studentDao.queryStudentByPhone(phone);
    }

    @Override
    public List<Student> queryAllStudent() {
        return studentDao.queryAllStudent();
    }

    @Override
    public int deleteStudentById(int id) {
        return studentDao.delectStudentById(id);
    }

    @Override
    public boolean updateStuInfo(Student student) {
        return studentDao.updateStuInfo(student);
    }

    @Override
    public Student queryStudentById(int id) {
        return studentDao.queryStudentById(id);
    }

    @Override
    public boolean addNoticeToStudentByAdmin(int studentId, String title, String content, String createTime) {
        return studentDao.addNoticeToStudentByAdmin(studentId,title,content,createTime);
    }

    @Override
    public List<Notice> queryNoticeByStudentId(int studentId) {
        return studentDao.queryNoticeByStudentId(studentId);
    }
    @Override
    public List<Student> queryStudent(String id, String name, String school, String sex) {
        return studentDao.queryStudent(id,name,school,sex);
    }
}
