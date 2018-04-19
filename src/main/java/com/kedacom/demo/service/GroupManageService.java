package com.kedacom.demo.service;

import com.kedacom.demo.model.Group;
import com.kedacom.demo.model.GroupTree;

import java.util.List;

public interface GroupManageService {
    /**
     * 查询分组列表
     * @return
     */
    List<Group> getGroupList();

    /**
     * 获取树结构数据
     * @return
     */
    List<GroupTree> getTreeData(List<Group> groupList);

    Group getGroupById(Integer id);
}
