package com.kedacom.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kedacom.demo.common.enums.OperatorEnum;
import com.kedacom.demo.common.utils.FileOperateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
    private final Logger logger = Logger.getLogger(this.getClass());
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

	/**
	 * 查询用户列表
	 * @param name
	 * @param status
	 * @param type
	 * @return
	 */
	@RequestMapping (value = "/queryList")
	public ModelAndView queryList(@RequestParam String name, @RequestParam Integer status, @RequestParam String type) {
		List<User> userList = userManageService.getUserList(name, status);
		String viewName = null;
		if (type.equals(OperatorEnum.USER_OPERATE.getName())) {
			viewName = "userManage/userContent";
		} else {
			viewName = "groupManage/selectedList";
		}
		ModelAndView view = new ModelAndView(viewName);
		view.addObject("userList" ,userList);
		return view;
	}


	/**
	 * 个人信息页
	 * @return
	 */
	@RequestMapping (value = "/baseInfoIndex")
	public ModelAndView baseInfoIndex(@RequestParam Integer id) {
		ModelAndView view = new ModelAndView("userManage/baseInfoIndex");
		User user = userManageService.getUserById(id);
		view.addObject("user",user);
		return view;
	}

	/**
	 * 编辑用户首页
	 * @param userId
	 * @return
	 */
	@RequestMapping (value = "/editUserIndex" , method = RequestMethod.GET)
	public ModelAndView editUserIndex(@RequestParam Integer userId) {
		ModelAndView view = new ModelAndView("userManage/editUserIndex");
		User user = userManageService.getUserById(userId);
		view.addObject("user",user);
		return view;
	}

	/**
	 * 创建用户页面
	 * @return
	 */
	@RequestMapping (value = "/createUserIndex" , method = RequestMethod.GET)
	public ModelAndView createUserIndex() {
		ModelAndView view = new ModelAndView("userManage/createUserIndex");
		return view;
	}

	/**
	 * 初始化用户头像
	 * @param userId
	 * @return
	 */
	@RequestMapping (value = "/initImage")
	public ModelAndView initImage(@RequestParam Integer userId) {
		 String imagePath = "";
		 ModelAndView view = new ModelAndView("/userManage/imageIndex");
		 User user = userManageService.getUserById(userId);
		 if (user != null) {
			 imagePath = user.getPhoto();
		 }
		 view.addObject("imagePath",imagePath);
		 return view;
	}

	/**
	 * 删除用户
	 * @param userId
	 */
	@RequestMapping (value = "/deleteUser" , method = RequestMethod.POST)
	@ResponseBody
	public void deleteUser(@RequestParam Integer userId) {
		userManageService.deleteById(userId);
	}

	/**
	 * 创建或更新用户
	 * @param user
	 * @return
	 */
	@RequestMapping (value = "/createOrUpdateUser" , method = RequestMethod.POST)
	@ResponseBody
	public Boolean createOrUpdateUser(User user) {
		Boolean info = null;
		try {
			info = userManageService.createOrUpdateUser(user);
		} catch(Exception e) {
			logger.error("创建用户异常:" + e);
		}
		return info;
	}

	/**
	 * 用户上传头像
	 * @param file
	 * @param userId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/uploadImage", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
	@ResponseBody
	public String uploadImage(@RequestParam(value = "file", required = false) MultipartFile file,
							 @RequestParam(value = "userId", required = false) Integer userId,
							 HttpSession session) {
		String imagePath = FileOperateUtil.uploadFile(file, session);
		User user = userManageService.getUserById(userId);
		if (!StringUtils.isEmpty(imagePath) && user != null) {
			user.setPhoto(imagePath);
			userManageService.createOrUpdateUser(user);
		}
		return imagePath;
	}
}
