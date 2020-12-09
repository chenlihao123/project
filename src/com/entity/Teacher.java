package com.entity;

/**
 * @author chenlihao
 * @create 2020-12-09 14:50
 */
public class Teacher {
    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String gender;
    private String phone;
    private String email;
    private String school;//所在学校
    private String department;//系别

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", school='" + school + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
