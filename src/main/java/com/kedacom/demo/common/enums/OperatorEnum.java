package com.kedacom.demo.common.enums;

/**
 * 操作类型枚举
 * @author 钱其清
 */
public enum OperatorEnum {
    CREATE("create", 1),
    EDIT("edit", 2),
    USER_OPERATE("user",3),
    GROUP_OPERATE("group",4);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private OperatorEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (OperatorEnum o : OperatorEnum.values()) {
            if (o.getIndex() == index) {
                return o.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
