package com.entity;

/**
 * @author chenlihao
 * @create 2020-12-21 15:30
 */
public class HomeworkManage {
    private Integer id;//homeworkId
    private String endTime;
    private String title;
    private Integer total;
    private String progress;

    public HomeworkManage() {
    }

    @Override
    public String toString() {
        return "HomeworkManage{" +
                "id=" + id +
                ", endTime='" + endTime + '\'' +
                ", title='" + title + '\'' +
                ", total=" + total +
                ", progress='" + progress + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
