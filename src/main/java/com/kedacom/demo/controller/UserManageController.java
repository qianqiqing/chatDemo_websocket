package com.kedacom.demo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kedacom.demo.model.User;
import com.kedacom.demo.service.UserManageService;

/**
 * 用户管理控制器
 * @Author 钱其清
 */
@Controller
@RequestMapping("/userManage")
public class UserManageController {

	@Autowired
	private UserManageService userManageService;

	/**
	 * 用户列表页
	 * @return
	 */
	@RequestMapping (value = "/userListIndex")
	public ModelAndView userListIndex() {
		ModelAndView view = new ModelAndView("userManage/userListIndex");
		return view;
	}

	@RequestMapping (value = "/chatRoomIndex")
	public ModelAndView chatRoomIndex() {
		ModelAndView view = new ModelAndView("index_test");
		return view;
	}

	/**
	 * 个人信息页
	 * @return
	 */
	@RequestMapping (value = "/baseInfoIndex")
	public ModelAndView baseInfoIndex(@RequestParam Integer userId) {
		ModelAndView view = new ModelAndView("userManage/baseInfoIndex");
		User user = userManageService.getUserById(userId);
		view.addObject("user",user);
		return view;
	}

	@RequestMapping (value = "/addUserIndex" , method = RequestMethod.GET)
	public ModelAndView addUserIndex() {
		ModelAndView view = new ModelAndView();
		view.setViewName("userManage/addUser");
		return view;
	}

	@RequestMapping (value = "/loadUser" , method = RequestMethod.GET)
	public ModelAndView loadUser(@RequestParam int userId) {
		ModelAndView view = new ModelAndView("userManage/userContent");
		User user = userManageService.getUserById(userId);
		view.addObject("user", user);
		return view;
	}

	/**
	 * 验证用户更新后是否需要更新session；修改用户名仅更新session；修改密码则需要重新登录
	 * @param userId
	 * @return
	 */
	@RequestMapping (value = "/validateSession" , method = RequestMethod.GET)
	public ModelAndView loadUser(HttpSession session , @RequestParam int userId) {
//		String jspUri;
//		User currentUser = userManageService.getUserById(userId);
//		User sessionUser = (User) session.getAttribute("currentUser");
//		if (currentUser != null) {
//			if (!currentUser.getPassword().equals(sessionUser.getPassword())) {
//
//			} else {
//				if(!currentUser.getName().equals(sessionUser.getName())) {
//
//				} else {
//					jspUri = "";
//				}
//			}
//		} else {
//			jspUri = "login/login";
//		}
		return null;
	}

	/**
	 * 更新用户信息
	 * @param user
	 */
	@RequestMapping (value = "/modifyUser" , method = RequestMethod.POST)
	@ResponseBody
	public int modifyUser(User user){
		userManageService.modifyUser(user);

		return user.getId();
	}

	/**
	 * 创建用户
	 * @param user
	 * @return
	 */
	@RequestMapping (value = "/createUser" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> createUser(User user) {
		try {
			userManageService.createUser(user);
		} catch(Exception e) {
			return new ResponseEntity<String>("创建用户失败！", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("创建用户成功！", HttpStatus.OK);
	}

	/**
	 * 下载文件
	 * @param response
	 * @param fileName
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping (value = "/downLoad")
	@ResponseBody
	public void downLoad(HttpServletResponse response, @RequestParam String fileName) throws UnsupportedEncodingException {
		userManageService.downLoad(response, java.net.URLDecoder.decode(fileName,"UTF-8"));
	}

}
