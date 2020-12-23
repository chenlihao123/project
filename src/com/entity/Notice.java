package com.entity;

/**
 * @author chenlihao
 * @create 2020-12-23 16:26
 */
public class Notice {
    private Integer id;
    private Integer studentId;
    private String title;
    private String content;
    private String createTime;
    private Integer isRead;

    public Notice() {
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isRead=" + isRead +
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
