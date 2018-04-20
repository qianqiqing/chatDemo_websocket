package com.kedacom.demo.service;

import com.kedacom.demo.model.User;

import java.util.List;

public interface UserGroupServie {
    /**
     * 某个分组下可分配的人员列表
     * @param groupId
     * @return
     */
    List<User> selectedUser(Integer groupId);
}
