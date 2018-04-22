package com.kedacom.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 聊天室控制器
 * @author 钱其清
 */
@Controller
@RequestMapping ("/chat")
public class ChatRoomController {

    /**
     * 聊天室首页
     * @return
     */
    @RequestMapping (value = "/chatRoomIndex")
    public ModelAndView chatRoomIndex() {
        ModelAndView view = new ModelAndView("singleChat");
        return view;
    }
}
