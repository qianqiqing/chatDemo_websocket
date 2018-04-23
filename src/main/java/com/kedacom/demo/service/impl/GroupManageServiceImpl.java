package com.kedacom.demo.service.impl;

import com.kedacom.demo.dao.GroupDao;
import com.kedacom.demo.dao.UserDao;
import com.kedacom.demo.dao.UserGroupDao;
import com.kedacom.demo.model.Group;
import com.kedacom.demo.model.GroupTree;
import com.kedacom.demo.model.User;
import com.kedacom.demo.model.UserGroup;
import com.kedacom.demo.service.GroupManageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 分组管理实现类
 * @author 钱其清
 */
@Service
public class GroupManageServiceImpl implements GroupManageService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserGroupDao userGroupDao;

    @Override
    public List<Group> getGroupList(){
        List groupList = null;
        try {
            groupList = groupDao.getGroupList();
        } catch (Exception e) {
            logger.error("查询分组异常：" + e);
        }
        return groupList;
    }

    @Override
    public List<GroupTree> getTreeData(List<Group> groupList) {
        List<GroupTree> treeList = new ArrayList<>();
        GroupTree rootNode = new GroupTree();
        rootNode.setText("用户分组");
        if (groupList != null && groupList.size()>0) {
            rootNode.setChildren(getChildNode(rootNode, groupList));
        }
        treeList.add(rootNode);
        return treeList;
    }

    @Override
    public Group getGroupById(Integer id) {
        return groupDao.selectByPrimaryKey(id);
    }

    @Override
    public void create(Group group) {
        groupDao.insert(group);
    }

    @Override
    public void update(Group group) {
        groupDao.updateByPrimaryKeySelective(group);
    }

    @Override
    public void deleteGroup(Integer id) {
        //查询该分组的子节点,将他们的父节点信息置空
        List<Group> allGroupList = groupDao.getGroupList();
        for (Group group : allGroupList) {
            if (Objects.equals(group.getParentId(),id)) {
                group.setParentId(null);
                groupDao.updateByPrimaryKeySelective(group);
            }
        }

        //删除用户-分组关联表中有关的数据
        List<UserGroup> userGroupList = userGroupDao.selectByGroupId(id);
        for (UserGroup userGroup : userGroupList) {
             userGroupDao.deleteByPrimaryKey(userGroup.getId());
        }

        //删除分组
        groupDao.deleteByPrimaryKey(id);
    }

    @Override
    public void assignUser(Integer groupId, List<Integer> userIds) {
        try {
            //查询已有的关联用户
            List<UserGroup> old_userGroup = userGroupDao.selectByGroupId(groupId);
            //删除原有的关联关系
            for (UserGroup userGroup : old_userGroup) {
                userGroupDao.deleteByPrimaryKey(userGroup.getId());
            }

            //添加新的关联关系
            for (Integer userId : userIds) {
                UserGroup userGroup = new UserGroup();
                userGroup.setGroupId(groupId);
                userGroup.setUserId(userId);
                userGroupDao.insert(userGroup);
            }
        } catch (Exception e) {
            logger.error("关联用户异常:" + e);
        }
    }

    /**
     * 递归获取子节点
     * @param node
     * @param groupList
     * @return
     */
    private List<GroupTree> getChildNode(GroupTree node, List<Group> groupList) {
        List<GroupTree> childNodeList = new ArrayList<>();
        for (Group group : groupList) {
            GroupTree childNode = new GroupTree();
            if (Objects.equals(group.getParentId(),node.getId())) {    //是子节点
                childNode.setId(group.getId());
                childNode.setText(group.getGroupName());
                childNode.setType("group");
                childNode.setChildren(this.getChildNode(childNode,groupList));
                childNodeList.add(childNode);
            }
        }

        //查询该分组下面的用户
        List<User> userList = userDao.selectByGroupId(node.getId());
        for (User user : userList) {
            GroupTree childNode = new GroupTree();
            childNode.setId(user.getId());
            childNode.setText(user.getName());
            childNode.setType("user");
            childNodeList.add(childNode);
        }
        return childNodeList;
    }

}
