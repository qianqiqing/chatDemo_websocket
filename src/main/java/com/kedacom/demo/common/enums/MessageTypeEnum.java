package com.kedacom.demo.common.enums;

/**
 * 消息内容类型枚举
 * @Author 钱其清
 */
public enum MessageTypeEnum {
    IMAGE("image"),     //图片
    FILE("file"),       //文件
    TEXT("text");       //文本
    // 成员变量
    private String name;
    // 构造方法
    private MessageTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
