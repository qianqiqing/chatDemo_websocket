package com.kedacom.demo.controller;

import com.kedacom.demo.common.enums.OperatorEnum;
import com.kedacom.demo.common.utils.JsonUtil;
import com.kedacom.demo.model.Group;
import com.kedacom.demo.model.GroupTree;
import com.kedacom.demo.model.User;
import com.kedacom.demo.service.GroupManageService;
import com.kedacom.demo.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    /**
     * 分组管理首页
     * @return
     */
    @RequestMapping (value = "/index")
    public ModelAndView groupIndex() {
        ModelAndView view = new ModelAndView("groupManage/index");
        return view;
    }

    /**
     * 分组树形结构页面
     * @return
     */
    @RequestMapping (value = "/initTree")
    public ModelAndView initTree() {
        ModelAndView view = new ModelAndView("groupManage/groupTree");
        return view;
    }

    /**
     * 分组详情
     * @param id
     * @param type
     * @return
     */
    @RequestMapping (value = "/groupDetail")
    public ModelAndView groupDetail(@RequestParam Integer id, @RequestParam String type){
        ModelAndView view = new ModelAndView("groupManage/groupContent");
        if (type.equals(OperatorEnum.CREATE.getName())) {
            view.addObject("parentId",id);
        } else {
            //分组详情
            Group group = groupManageService.getGroupById(id);
            //人员选择列表
            List<User> selectedUser = userManageService.selectedUser(id);

            view.addObject("userList",selectedUser);
            view.addObject("group",group);
        }
        view.addObject("type", type);
        return view;
    }

    /**
     * 分组中用户信息
     * @param id
     * @return
     */
    @RequestMapping (value = "/userDetail")
    public ModelAndView userDetail(@RequestParam Integer id){
        ModelAndView view = new ModelAndView("groupManage/userDetail");
        User user = userManageService.getUserById(id);
        view.addObject("user",user);
        return view;
    }

    /**
     * 加载分组树节点数据
     * @return
     */
    @RequestMapping (value = "/treeData")
    @ResponseBody
    public String treeData() {
        List<Group> groupList = groupManageService.getGroupList();
        List<GroupTree> groupTree = groupManageService.getTreeData(groupList);
        return JsonUtil.toJson(groupTree);
    }

    /**
     * 创建或编辑分组
     * @param type
     * @param group
     */
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

    /**
     * 删除分组
     * @param id
     */
    @RequestMapping (value = "/deleteGroup", method = RequestMethod.POST)
    @ResponseBody
    public void deleteGroup(@RequestParam Integer id){
        try {
            groupManageService.deleteGroup(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 分配用户
     * @param groupId
     * @param ids
     */
    @RequestMapping (value = "/assignUser/{groupId}" ,method = RequestMethod.POST)
    @ResponseBody
    public void assignUser(@PathVariable("groupId") String groupId, @RequestBody List<Integer> ids) {
        groupManageService.assignUser(Integer.valueOf(groupId), ids);
    }
}
