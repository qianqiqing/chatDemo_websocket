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

    /**
     * 根据id获得分组信息
     * @param id
     * @return
     */
    Group getGroupById(Integer id);

    /**
     * 创建分组
     * @param group
     */
    void create(Group group);

    /**
     * 更新
     * @param group
     */
    void  update(Group group);

    /**
     * 删除分组
     * @param id
     */
    void deleteGroup(Integer id);

    void assignUser(Integer groupId, List<Integer> userIds);
}
