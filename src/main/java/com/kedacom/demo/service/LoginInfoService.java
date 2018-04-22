package com.kedacom.demo.service;

import com.kedacom.demo.model.User;

/**
 * 登陆接口
 * @author 钱其清
 */
public interface LoginInfoService {
	/**
	 * 验证登录信息
	 * @param name
	 * @param password
	 * @return
	 */
	public User validateUser(String name, String password);

}
