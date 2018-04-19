package com.kedacom.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.kedacom.demo.common.utils.JsonUtil;
import com.kedacom.demo.model.Group;
import com.kedacom.demo.model.GroupTree;
import com.kedacom.demo.model.User;
import com.kedacom.demo.service.GroupManageService;
import com.kedacom.demo.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 分组管理控制器
 * @Author 钱其清
 */
@Controller
@RequestMapping("/groupManage")
public class GroupManageController {
    @Autowired
    private GroupManageService groupManageService;
    @Autowired
    private UserManageService userManageService;

    @RequestMapping (value = "/index")
    public ModelAndView groupIndex() {
        ModelAndView view = new ModelAndView("groupManage/index");
        return view;
    }

    @RequestMapping (value = "/initTree")
    public ModelAndView initTree() {
        ModelAndView view = new ModelAndView("groupManage/groupTree");
        return view;
    }

    @RequestMapping (value = "/groupDetail")
    public ModelAndView groupDetail(@RequestParam Integer id){
        Group group = groupManageService.getGroupById(id);
        ModelAndView view = new ModelAndView("groupManage/groupDetail");
        view.addObject("group",group);
        return view;
    }

    @RequestMapping (value = "/assignUserIndex")
    public String assignUser(Model model, @RequestParam Integer id) {
        List<User> userList = userManageService.getUserList("",null);
        model.addAttribute("userList", userList);
        return "groupManage/assignUserIndex";
    }

    @RequestMapping (value = "/treeData")
    @ResponseBody
    public String treeData() {
        List<Group> groupList = groupManageService.getGroupList();
        List<GroupTree> groupTree = groupManageService.getTreeData(groupList);
        return JsonUtil.toJson(groupTree);
    }
}
