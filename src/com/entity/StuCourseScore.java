package com.entity;

/**
 * @author chenlihao
 * @create 2020-12-22 19:25
 */
public class StuCourseScore {
    private Double courseScore;
    private Double homeworkScore;
    private Double videoScore;

    public StuCourseScore() {
    }

    @Override
    public String toString() {
        return "StuCourseScore{" +
                "courseScore=" + courseScore +
                ", homeworkScore=" + homeworkScore +
                ", videoScore=" + videoScore +
                '}';
    }

    public Double getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(Double courseScore) {
        this.courseScore = courseScore;
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
}
