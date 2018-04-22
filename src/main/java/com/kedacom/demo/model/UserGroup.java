package com.kedacom.demo.model;

/**
 * 用户分组关联关系
 * @author 钱其清
 */
public class UserGroup {
    //关联数据id
    private Integer id;

    //用户id
    private Integer userId;

    //分组id
    private Integer groupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
