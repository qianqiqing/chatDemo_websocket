package com.kedacom.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kedacom.demo.model.User;

/**
 * 用户管理接口
 * @author 钱其清
 */
public interface UserManageService {
	/**
	 * 创建或更新用户
	 * @param user
	 * @return
	 */
	public boolean createOrUpdateUser(User user);

	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	public User getUserById(int id);

	/**
	 * 条件查询用户列表
	 * @param name
	 * @param status
	 * @return
	 */
	public List<User> getUserList(String name, Integer status);

	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteById(Integer id);

	/**
	 * 某个分组下可分配的人员列表
	 * @param groupId
	 * @return
	 */
	List<User> selectedUser(Integer groupId);

	List<User> onLineUser();
}
