package com.kedacom.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.kedacom.demo.common.utils.Base64OperateUtil;
import com.kedacom.demo.common.utils.ConstantDefine;
import com.kedacom.demo.dao.UserGroupDao;
import com.kedacom.demo.model.UserGroup;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kedacom.demo.dao.UserDao;
import com.kedacom.demo.model.User;
import com.kedacom.demo.service.UserManageService;

/**
 * 管理用户的service实现类
 * @author 钱其清
 */
@Service
public class UserManageServiceImpl implements UserManageService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserGroupDao userGroupDao;

	@Override
	public boolean createOrUpdateUser(User user) {
		User queryUser = userDao.selectByName(user.getName());
		//验证是否重名
		boolean validateInfo = validate(user, queryUser);
		if (!validateInfo) {
			String password = user.getPassword();
			//更新操作
			if (user.getId() != null) {
				//如果密码没有加密则标识修改了密码
				if (!Base64OperateUtil.ifBase(password)) {
					user.setPassword(Base64OperateUtil.jdkBase64Encoder(password));
				}
				userDao.updateByPrimaryKey(user);
			}  else {           //新增操作
				//base64对密码加密
				user.setPassword(Base64OperateUtil.jdkBase64Encoder(password));
				userDao.insert(user);
			}
		}
		return validateInfo;
	}

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

    @Override
    public List<User> onLineUser() {
        return userDao.getOnlineUser();
    }

    @Override
	public User getUserById(int id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public List<User> getUserList(String name, Integer status) {
		List<User> allUser = userDao.getQueryUser(name,status);
        return allUser;
	}

	@Override
	public void deleteById(Integer id) {
		userDao.deleteByPrimaryKey(id);
	}



	/**
	 * 验证重名用户
	 * @param user
	 * @return
	 */
	private boolean validate(User user, User queryUser) {
		if (queryUser != null) {
			if (queryUser.getId().equals(user.getId())) {
				return false;
			} else {
                return true;
			}
		}
		return false;
	}

}
