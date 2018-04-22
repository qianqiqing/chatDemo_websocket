package com.kedacom.demo.model;

import java.util.List;

/**
 * 分组树节点实体类
 * @author 钱其清
 */
public class GroupTree {
    //节点id
    private Integer id;

    //节点名称
    private String text;

    //节点类型
    private String type;

    //子节点
    private List<GroupTree> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<GroupTree> getChildren() {
        return children;
    }

    public void setChildren(List<GroupTree> children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
