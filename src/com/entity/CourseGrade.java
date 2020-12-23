package com.entity;

/**
 * @author chenlihao
 * @create 2020-12-17 15:06
 */
public class CourseGrade {
    private Integer id;
    private Integer studentId;
    private String studentName;
    private String studentImg;
    private Integer courseId;
    private String courseName;
    private String content;
    private Integer grade;
    private String createTime;

    public CourseGrade() {
    }

    public CourseGrade(Integer studentId, String studentName, String studentImg, Integer courseId, String courseName, String content, Integer grade, String createTime) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentImg = studentImg;
        this.courseId = courseId;
        this.courseName = courseName;
        this.content = content;
        this.grade = grade;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CourseGrade{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentImg='" + studentImg + '\'' +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", content='" + content + '\'' +
                ", grade=" + grade +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public String getStudentImg() {
        return studentImg;
    }

    public void setStudentImg(String studentImg) {
        this.studentImg = studentImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
