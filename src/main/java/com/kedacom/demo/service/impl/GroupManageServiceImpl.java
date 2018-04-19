package com.kedacom.demo.service.impl;

import com.kedacom.demo.dao.GroupDao;
import com.kedacom.demo.dao.UserDao;
import com.kedacom.demo.model.Group;
import com.kedacom.demo.model.GroupTree;
import com.kedacom.demo.model.User;
import com.kedacom.demo.service.GroupManageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupManageServiceImpl implements GroupManageService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private UserDao userDao;

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
        rootNode.setChildren(getChildNode(rootNode, groupList));
        treeList.add(rootNode);
        return treeList;
    }

    @Override
    public Group getGroupById(Integer id) {
        return groupDao.selectByPrimaryKey(id);
    }

    private List<GroupTree> getChildNode(GroupTree node, List<Group> groupList) {
        List<GroupTree> childNodeList = new ArrayList<>();
        for (Group group : groupList) {
            GroupTree childNode = new GroupTree();
            if (group.getParentId() == node.getId()) {    //是子节点
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
