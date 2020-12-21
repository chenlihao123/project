package com.dao.impl;

import com.dao.BaseDao;
import com.dao.StudentDao;
import com.entity.Student;

import java.util.List;

/**
 * @author chenlihao
 * @create 2020-12-09 15:21
 */
public class StudentDaoImpl extends BaseDao implements StudentDao {

    @Override
    public Student queryByUserNameAndPassword(String username, String password) {
        String sql="select * from student where username = ? and password = ?";
        return queryForOne(Student.class,sql,username,password);
    }

    @Override
    public boolean addStudent(Student student) {
        String sql="insert into student (`username`,`password`,`realName`,`gender`,`phone`,`email`,`school`,`department`,`classId`,imgPath) values (?,?,?,?,?,?,?,?,?,?)";
        int i = update(sql, student.getUsername(), student.getPassword(), student.getRealName(), student.getGender(), student.getPhone(), student.getEmail(), student.getSchool(), student.getDepartment(), student.getClassId(),student.getImgPath());
        if(i>=0){
            return true;
        }
        return false;
    }

    @Override
    public Student queryStudentByPhone(String phone) {
        String sql="select * from student where phone = ?";
        return queryForOne(Student.class,sql,phone);
    }

    @Override
    public Student queryStudentByEmail(String email) {
        String sql="select * from student where email = ?";
        return queryForOne(Student.class,sql,email);
    }

    @Override
    public Student queryStudentByUsername(String username) {
        String sql="select * from student where username = ?";
        return queryForOne(Student.class,sql,username);
    }

    @Override
    public List<Student> queryAllStudent() {
        String sql = "SELECT * FROM `student`";
        return queryForList(Student.class,sql);
    }

    @Override
    public int delectStudentById(int id) {
        String sql = "DELETE FROM `student` WHERE id = ?";
        return update(sql,id);
    }

    @Override
    public boolean updateStuInfo(Student s) {
        String sql="update student set username=?,password=?,realName=?,gender=?,phone=?,email=?,school=?,department=?,classId=?,imgPath=? where id=?";
        int i = update(sql, s.getUsername(), s.getPassword(), s.getRealName(), s.getGender(), s.getPhone(), s.getEmail(), s.getSchool(), s.getDepartment(), s.getClassId(), s.getImgPath(),s.getId());
        return i>=0?true:false;
    }


//    测试sql语句是否正确
//    public static void main(String[] args) {
//        StudentDaoImpl studentDao = new StudentDaoImpl();
////        System.out.println(new StudentDaoImpl().queryByUserNameAndPassword("123456", "123456"));
////        System.out.println(studentDao.addStudent(new Student("", "", "", "", "", "", "", "", 1)));
////        System.out.println(studentDao.queryStudentByPhone("123456"));
////        System.out.println(studentDao.queryStudentByEmail("123456"));
////        System.out.println(studentDao.queryStudentByUsername("123456"));
//    }
}
