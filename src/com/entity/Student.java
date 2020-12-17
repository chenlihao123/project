package com.entity;

/**
 * @author chenlihao
 * @create 2020-12-09 14:46
 */
public class Student {
    private Integer id;
    private String username;
    private String password;
    private String realName;//真实姓名
    private String gender;//性别
    private String phone;
    private String email;
    private String school;
    private String department;//系别
    private Integer classId;//所在班级
    private String imgPath;//学生头像

    public Student() {
    }

    public Student(Integer id, String username, String password, String realName, String gender, String phone, String email, String school, String department, Integer classId, String imgPath) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.school = school;
        this.department = department;
        this.classId = classId;
        this.imgPath = imgPath;
    }

    public Student(String username, String password, String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", school='" + school + '\'' +
                ", department='" + department + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
