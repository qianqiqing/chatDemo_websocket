package com.kedacom.demo.controller;

import com.kedacom.demo.model.User;
import com.kedacom.demo.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 聊天室控制器
 * @author 钱其清
 */
@Controller
@RequestMapping ("/chat")
public class ChatRoomController {
    @Autowired
    private UserManageService userManageService;

    /**
     * 聊天室首页
     * @return
     */
    @RequestMapping (value = "/chatRoomIndex")
    public ModelAndView chatRoomIndex() {
        List<User> onLineUser = userManageService.onLineUser();
        ModelAndView view = new ModelAndView("singleChat");
        view.addObject("onLineUser" , onLineUser);
        view.addObject("size" , onLineUser.size());
        return view;
    }
}
