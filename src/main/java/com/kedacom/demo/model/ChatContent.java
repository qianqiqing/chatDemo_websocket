package com.kedacom.demo.model;

/**
 * 用户会话实体类
 * @author 钱其清
 */
public class ChatContent {
    //发送人
    private String from;

    //接受人
    private String to;

    //消息类型
    private String messageType;

    //消息内容
    private String message;

    //发送的文件路径
    private String filePath;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
