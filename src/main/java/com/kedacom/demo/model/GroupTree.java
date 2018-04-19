package com.kedacom.demo.model;

import java.util.List;

public class GroupTree {
    private Integer id;

    private String text;

    private String type;

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
