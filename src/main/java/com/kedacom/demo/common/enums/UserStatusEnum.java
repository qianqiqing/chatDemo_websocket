package com.kedacom.demo.common.enums;

public enum  UserStatusEnum {
    LOGIN(1),     //已登录
    LOGOUT(0);       //未登录
    // 成员变量
    private Integer name;
    // 构造方法
    private UserStatusEnum(Integer name) {
        this.name = name;
    }

    public Integer getName() {
        return name;
    }
}
