package com.entity;

/**
 * @author chenlihao
 * @create 2020-12-09 15:03
 * 邮件信息
 */
public class Message {
    private Integer id;
    private String fromEmail;//发送人
    private String toEmail;//收件人
    private String messageTitle;//邮件题目
    private String messageContent;//邮件内容
    private String filePath;//附件路径
    private String createTime;//发送时间

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromEmail='" + fromEmail + '\'' +
                ", toEmail='" + toEmail + '\'' +
                ", messageTitle='" + messageTitle + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", filePath='" + filePath + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
