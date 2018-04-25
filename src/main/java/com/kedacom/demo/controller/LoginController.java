package com.kedacom.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kedacom.demo.common.enums.OperatorEnum;
import com.kedacom.demo.common.enums.UserStatusEnum;
import com.kedacom.demo.common.utils.Base64OperateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kedacom.demo.model.User;
import com.kedacom.demo.service.LoginInfoService;
import com.kedacom.demo.service.UserManageService;

import java.io.IOException;
/**
 * 登陆管理
 * @Author 钱其清
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginInfoService loginInfoService;
	
	@Autowired
	private UserManageService userManageService;

	/**
	 * 登陆首页
	 * @return
	 */
	@RequestMapping (method = RequestMethod.GET)  
    public ModelAndView login() {
		ModelAndView view = new ModelAndView("login/login");
        return view;   
    }

	/**
	 * 登陆验证
	 * @param session
	 * @param name
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping (value = "/loginValidate")
	public String loginValidate(HttpSession session , String name, String password, Model model) {
		User user = loginInfoService.validateUser(name, password);
		if (user != null) {
			session.setAttribute("currentUser", user);
			return "index";
		} else if (session.getAttribute("currentUser") != null) {
			return "index";
		} else {
			return "login/login";
		}
	}

	/**
	 * 退出登陆
	 * @param request
	 * @return
	 */
	@RequestMapping (value = "/logOut", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request){
		//改变用户状态
		User user = (User) request.getSession().getAttribute("currentUser");
		User currentUser = userManageService.getUserById(user.getId());
		if (currentUser != null) {
			currentUser.setStatus(UserStatusEnum.LOGOUT.getName());
			userManageService.createOrUpdateUser(currentUser);
		}

		//移除session
		request.getSession().removeAttribute("currentUser");
		request.getSession().invalidate();
		return "login/login";
	}
}
