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
    <div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人资料</strong> / <small>Personal information</small></div>
    </div>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
            <div class="am-panel am-panel-default">
                <div class="am-panel-bd">
                    <div class="am-g">
                        <div class="am-u-md-4">
                            <img class="am-img-circle am-img-thumbnail" src="http://amui.qiniudn.com/bw-2014-06-19.jpg?imageView/1/w/1000/h/1000/q/80" alt=""/>
                        </div>
                        <div class="am-u-md-8">
                            <p>你可以本地上传头像。 </p>
                            <form class="am-form">
                                <div class="am-form-group">
                                    <input type="file" id="user-pic">
                                    <p class="am-form-help">请选择要上传的文件...</p>
                                    <button type="button" class="am-btn am-btn-primary am-btn-xs">保存</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

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
                        <input type="text" id="edit_phone" placeholder="输入电话号码">
                    </div>
                </div>

                <div class="am-form-group">
                    <label class="am-u-sm-3 am-form-label">角色权限</label>
                    <div class="am-u-sm-9">
                        <select id="edit_role">
                            <option value="1" <c:if test="${user.role == 1}">selected</c:if>>管理员</option>
                            <option value="0" <c:if test="${user.role == 0}">selected</c:if>selected>普通用户</option>
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
            "phone" : $("#edit_phone").val();
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
</script>

