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
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form class="am-form am-form-horizontal">
            <div class="am-form-group">
                <label for="edit_name" class="am-u-sm-3 am-form-label">输入用户名</label>
                <div class="am-u-sm-9">
                    <input type="text" id="edit_name" placeholder="必填项" value="${user.name}">
                </div>
            </div>

            <div class="am-form-group">
                <label for="edit_name" class="am-u-sm-3 am-form-label">输入密码</label>
                <div class="am-u-sm-9">
                    <input type="password" id="edit_password" placeholder="必填项" value="${user.password}">
                </div>
            </div>

            <div class="am-form-group">
                <label for="edit_email" class="am-u-sm-3 am-form-label">电子邮件</label>
                <div class="am-u-sm-9">
                    <input type="email" id="edit_email" placeholder="输入邮箱" value="${user.email}">
                </div>
            </div>

            <div class="am-form-group">
                <label for="edit_phone" class="am-u-sm-3 am-form-label">电话</label>
                <div class="am-u-sm-9">
                    <input type="text" id="edit_phone" placeholder="输入电话号码" value="${user.phone}">
                </div>
            </div>

            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">角色权限</label>
                <div class="am-u-sm-9">
                    <select id="edit_role">
                        <option value="1" <c:if test="${user.role == 1}">selected</c:if>>管理员</option>
                        <option value="0" <c:if test="${user.role == 0}">selected</c:if>>selected>普通用户</option>
                    </select>
                </div>
            </div>

            <div class="am-form-group">
                <div class="am-u-sm-9 am-u-sm-push-3">
                    <button type="button" class="am-btn am-btn-primary" onclick="saveModify()">保存修改</button>
                </div>
            </div>
        </form>
    </div>
</div>
</html>
<script type="application/javascript">
    function saveModify(){
        var user= {
            "id" : "${user.id}",
            "name" : $("#edit_name").val(),
            "password" : $("#edit_password").val(),
            "email" : $("#edit_email").val(),
            "phone" : $("#edit_phone").val(),
            "photo" : "${user.photo}",
            "group" : "${user.group}",
            "role" : $("#edit_role").val(),
            "status" : 1
        };

        $.ajax({
            url : webDemo.formatUrl("/userManage/modifyUser"),
            type: "POST",
            data : user,
            success : function(result){
                $("#success").modal();
            },
            error : function(e){
                $("#failed").modal();
            }
        })
    }

    function uploadImage() {
        debugger
        var userId = "${user.id}";
        var formData = new FormData();
        var fileElement = document.getElementById('image_file');
        var file = fileElement.files[0];
        formData.append("file",file);
        formData.append("userId",userId);
        $.ajax({
            url : webDemo.formatUrl("/userManage/uploadImage"),
            type: "POST",
            data : formData,
            processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
            contentType : false,  //必须false才会自动加上正确的Content-Type
            success : function(result){
                initImage();
            },
            error : function(e){
                $("#failed").modal();
            }
        })
    }

    function initImage(){
        var userId = "${user.id}";
        $.ajax({
            url : webDemo.formatUrl("/userManage/initImage"),
            type: "GET",
            data : {
                userId : userId
            },
            success : function(result){
                $("#user_image").html(result);
            },
            error : function(e){
            }
        })
    }
</script>

