package com.kedacom.demo.model;

/**
 * 分组实体类
 * @author 钱其清
 */
public class Group {
    //分组id
    private Integer id;

    //分组名称
    private String groupName;

    //分组描述
    private String description;

    //上级分组id
    private Integer parentId;

    //分组创建者id
    private Integer adminId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
