<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqiqing
  Date: 2018/4/18
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <div class="">
        <form class="am-form am-form-horizontal">
            <div class="am-form-group">
                <label for="group_name" class="am-u-sm-3 am-form-label">分组名称</label>
                <div class="am-u-sm-9">
                    <input type="text" id="group_name" placeholder="必填项" value="${group.groupName}">
                </div>
            </div>

            <div class="am-form-group">
                <label for="group_description" class="am-u-sm-3 am-form-label">分组描述</label>
                <div class="am-u-sm-9">
                    <input type="text" id="group_description" value="${user.description}">
                </div>
            </div>

            <div class="am-form-group">
                <div class="am-u-sm-9 am-u-sm-push-3">
                    <button type="button" class="am-btn am-btn-primary" onclick="editGroup()">保存修改</button>
                    <button type="button" class="am-btn am-btn-primary" onclick="addUser()">分配用户</button>
                </div>
            </div>
        </form>
</div>
<script type="application/javascript">
     function editGroup(){
         var group = {
             id : "${group.id}",
             groupName : $("#group_name").val(),
             description : $("#group_description").val(),
             parentId : "${group.parentId}"
         }

         $.ajax({
             url : webDemo.formatUrl("/groupManage/updateGroup"),
             data : group,
             success : function(result){
                 GroupManage.initTree();
             },
             error : function(e){

             }
         });
     }

     function addUser(){
         var groupId = "${group.id}";
         $.ajax({
             url : webDemo.formatUrl("/groupManage/assignUserIndex"),
             data : {
                 id : groupId
             },
             success : function(result){
                 $("#dialog #dialogContent").html(result);
                 $("#dialog").dialog("open");
             },
             error : function(e){

             }
         });
     }
</script>
<html>