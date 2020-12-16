package com.entity;

/**
 * @author chenlihao
 * @create 2020-12-09 14:57
 * 学生选课表
 */

public class StuCourse {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Double totalScore;//总成绩
    private Double homeworkScore;//作业成绩
    private Double videoScore;//视频成绩
    private Double videoProgress;//课程视频进度
    private Integer isPass;

    public StuCourse() {
    }

    @Override
    public String toString() {
        return "StuCourse{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", totalScore=" + totalScore +
                ", homeworkScore=" + homeworkScore +
                ", videoScore=" + videoScore +
                ", videoProgress=" + videoProgress +
                ", isPass=" + isPass +
                '}';
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getHomeworkScore() {
        return homeworkScore;
    }

    public void setHomeworkScore(Double homeworkScore) {
        this.homeworkScore = homeworkScore;
    }

    public Double getVideoScore() {
        return videoScore;
    }

    public void setVideoScore(Double videoScore) {
        this.videoScore = videoScore;
    }

    public Double getVideoProgress() {
        return videoProgress;
    }

    public void setVideoProgress(Double videoProgress) {
        this.videoProgress = videoProgress;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }
}
