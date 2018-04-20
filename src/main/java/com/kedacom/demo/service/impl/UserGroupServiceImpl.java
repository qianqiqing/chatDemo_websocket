package com.kedacom.demo.service.impl;

import com.kedacom.demo.dao.UserDao;
import com.kedacom.demo.dao.UserGroupDao;
import com.kedacom.demo.model.User;
import com.kedacom.demo.model.UserGroup;
import com.kedacom.demo.service.UserGroupServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserGroupServiceImpl implements UserGroupServie {

    @Autowired
    private UserGroupDao userGroupDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> selectedUser(Integer groupId) {
        //所有用户
        List<User> allUser = userDao.getAllUser();
        //查询该分组下已经分配的用户
        List<UserGroup> userGroupList = userGroupDao.selectByGroupId(groupId);
        List<Integer> selectedIds = new ArrayList<>();
        for (UserGroup userGroup : userGroupList) {
            selectedIds.add(userGroup.getUserId());
        }

        //组合已关联和未关联用户
        for (User user : allUser) {
            if (selectedIds.contains(user.getId())) {
                user.setSelected(true);
            }
        }

        return allUser;
    }
}
