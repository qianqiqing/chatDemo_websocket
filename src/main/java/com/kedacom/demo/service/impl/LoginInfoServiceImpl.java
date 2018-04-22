package com.kedacom.demo.service.impl;

import com.kedacom.demo.common.utils.Base64OperateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.kedacom.demo.dao.UserDao;
import com.kedacom.demo.model.User;
import com.kedacom.demo.service.LoginInfoService;

/**
 * 登陆实现类
 * @author 钱其清
 */
@Service
public class LoginInfoServiceImpl implements LoginInfoService {
	private Logger logger = Logger.getLogger(LoginInfoServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	public User validateUser(String name, String password) {
		logger.info("测试logger");
		try {
			if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(password)) {
				User user = userDao.selectByName(name);
				if (user != null) {
					String userPassword = user.getPassword();
					//如果密码是加密过的则解密
					if (Base64OperateUtil.ifBase(userPassword)) {
						userPassword = Base64OperateUtil.jdkBase64Decoder(user.getPassword());
					}
					if (password.equals(userPassword)) {
						user.setStatus(1);
						userDao.updateByPrimaryKey(user);
						return user;
					}
				}
			}
		} catch (Exception e) {
			logger.error("登陆验证异常:" + e);
		}
		return null;
	}

}
