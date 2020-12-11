package com.entity;

/**
 * @author chenlihao
 * @create 2020-12-09 14:55
 * 课程表
 */
public class Course {
    private Integer id;
    private String courseName;
    private Integer teacherId;
    private String school;
    private String department;//系别
    private Integer isFree;//0付费1免费
    private String imgPath;//课程封面路径
    private String courseInfo;//课程说明

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", school='" + school + '\'' +
                ", department='" + department + '\'' +
                ", isFree=" + isFree +
                ", imgPath='" + imgPath + '\'' +
                ", courseInfo='" + courseInfo + '\'' +
                '}';
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
