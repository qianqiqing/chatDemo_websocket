<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<input type="hidden" name="" id="groupId" value="${group.id}" />
<section>
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
                <input type="text" id="group_description" value="${group.description}">
            </div>
        </div>

        <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
                <button type="button" class="am-btn am-btn-primary" onclick="editGroup()">保存</button>
                <%--<button type="button" class="am-btn am-btn-primary" onclick="addUser()">分配用户</button>--%>
            </div>
        </div>
    </form>
</section>
<script type="application/javascript">
    var type = "${type}";
     function editGroup(){
         debugger
         var group;
         if("create" == type){
             group = {
                 id : null,
                 groupName : $("#group_name").val(),
                 description : $("#group_description").val(),
                 parentId : "${parentId}"
             }
         }else{
             group = {
                 id : "${group.id}",
                 groupName : $("#group_name").val(),
                 description : $("#group_description").val(),
                 parentId : "${group.parentId}"
             }
         }
         $.ajax({
             url : webDemo.formatUrl("/groupManage/saveOrUpdate/"+type+""),
             type : "POST",
             data : group,
             success : function(result){
                 GroupManage.initTree();

             },
             error : function(e){

             }
         });
     }
</script>
