package com.entity;

/**
 * @author chenlihao
 * @create 2020-12-09 14:59
 * 课程视频表
 */
public class CourseVideo {
    private Integer id;
    private Integer studentId;//学生id
    private Integer courseId;//课程id
    private String videoName;//视频名称
    private String imgPath;//封面路径
    private String videoPath;//视频路径
    private String videoInfo;//视频说明
    private Integer isFinish;//是否完成 0没完成1完成

    public CourseVideo() {
    }

    @Override
    public String toString() {
        return "CourseVideo{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", videoName='" + videoName + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", videoInfo='" + videoInfo + '\'' +
                ", isFinish=" + isFinish +
                '}';
    }

    public String getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(String videoInfo) {
        this.videoInfo = videoInfo;
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

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }
}
