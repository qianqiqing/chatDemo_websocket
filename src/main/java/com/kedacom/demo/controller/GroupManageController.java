package com.kedacom.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.kedacom.demo.common.enums.OperatorEnum;
import com.kedacom.demo.common.utils.JsonUtil;
import com.kedacom.demo.model.Group;
import com.kedacom.demo.model.GroupTree;
import com.kedacom.demo.model.User;
import com.kedacom.demo.service.GroupManageService;
import com.kedacom.demo.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView groupDetail(@RequestParam Integer id, @RequestParam String type){
        ModelAndView view = new ModelAndView("groupManage/groupDetail");
        if (type.equals(OperatorEnum.CREATE.getName())) {
            view.addObject("parentId",id);
        } else {
            Group group = groupManageService.getGroupById(id);
            view.addObject("group",group);
        }
        view.addObject("type", type);
        return view;
    }

    @RequestMapping (value = "/userDetail")
    public ModelAndView userDetail(@RequestParam Integer id){
        ModelAndView view = new ModelAndView("groupManage/userDetail");
        User user = userManageService.getUserById(id);
        view.addObject("user",user);
        return view;
    }

    @RequestMapping (value = "/selectListIndex")
    public ModelAndView selectListIndex (){
        ModelAndView view = new ModelAndView("groupManage/selectedList");
        List<User> userList = userManageService.getUserList("",null);
        view.addObject("userList", userList);
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

    @RequestMapping (value = "/saveOrUpdate/{type}", method = RequestMethod.POST)
    @ResponseBody
    public void saveOrUpdate(@PathVariable("type") String type, Group group) {
        try {
            if (type.equals(OperatorEnum.CREATE.getName())) {
                groupManageService.create(group);
            } else {
                groupManageService.update(group);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping (value = "/deleteGroup", method = RequestMethod.POST)
    @ResponseBody
    public void deleteGroup(@RequestParam Integer id){
        try {
            groupManageService.deleteGroup(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping (value = "/assignUser/{groupId}" ,method = RequestMethod.POST)
    @ResponseBody
    public void assignUser(@PathVariable("groupId") String groupId, @RequestBody List<Integer> ids) {
        groupManageService.assignUser(Integer.valueOf(groupId), ids);
    }
}
