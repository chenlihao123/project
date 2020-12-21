package com.entity;

/**
 * @author chenlihao
 * @create 2020-12-20 16:47
 */
public class StuHomework {
    private Integer id;
    private Integer studentId;
    private Integer homeworkId;
    private Integer courseId;
    private String content;
    private String file;
    private String createTime;
    private Double score;

    public StuHomework() {
    }

    @Override
    public String toString() {
        return "StuHomework{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", homeworkId=" + homeworkId +
                ", courseId=" + courseId +
                ", content='" + content + '\'' +
                ", file='" + file + '\'' +
                ", createTime='" + createTime + '\'' +
                ", score=" + score +
                '}';
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
