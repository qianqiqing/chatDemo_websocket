<%--
  Created by IntelliJ IDEA.
  User: qianqiqing
  Date: 2018/4/18
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" id="userId" value="${user.id}" />
<div class="am-popup-inner">
    <div class="am-popup-hd">
        <h4 class="am-popup-title">编辑用户</h4>
        <span data-am-modal-close
              class="am-close">
	      </span>
    </div>
    <div class="am-popup-bd" id="edit_user">
        <form class="am-form am-form-horizontal" >
            <fieldset>
                <div class="am-form-group">
                    <label for="name" class="am-u-sm-2 am-form-label">用户名</label>
                    <div class="am-u-sm-10">
                        <input type="text" id="name" placeholder="输入用户名" value="${user.name}">
                    </div>
                </div>

                <div class="am-form-group">
                    <label for="phone" class="am-u-sm-2 am-form-label">电话</label>
                    <div class="am-u-sm-10">
                        <input type="text" id="phone" placeholder="输入手机号" value="${user.phone}">
                    </div>
                </div>

                <div class="am-form-group">
                    <label for="password" class="am-u-sm-2 am-form-label">密码</label>
                    <div class="am-u-sm-10">
                        <input type="password" id="password" placeholder="输入密码" value="${user.password}">
                    </div>
                </div>

                <div class="am-form-group">
                    <label for="email" class="am-u-sm-2 am-form-label">邮箱</label>
                    <div class="am-u-sm-10">
                        <input type="email" id="email" placeholder="输入邮箱" value="${user.email}">
                    </div>
                </div>

                <div class="am-form-group">
                    <label for="role" class="am-u-sm-2 am-form-label">角色</label>
                    <div class="am-form-group am-form-select am-u-sm-10">
                        <select id="role" class="">
                            <option value="1" <c:if test="${user.role == 1}">selected</c:if>>管理员</option>
                            <option value="0" <c:if test="${user.role == 0}">selected</c:if>>普通用户</option>
                        </select>
                    </div>
                </div>
            </fieldset>

            <button type="button" class="am-btn am-btn-primary am-btn-block" id="edit_save">保存编辑</button>
        </form>
    </div>
</div>
<script type="application/javascript">
    $(function(){
        $("#edit_save").on("click",function(){
            var user= {
                "id" : $("#userId").val(),
                "name" : $("#edit_user #name").val(),
                "email" : $("#edit_user #email").val(),
                "photo" : "${user.photo}",
                "password" : $("#password").val(),
                "group" : "${user.group}",
                "status" : "${user.status}",
                "phone" : $("#edit_user #phone").val(),
                "role" : $("#edit_user #role").val(),
            };

            $.ajax({
                url : webDemo.formatUrl("/userManage/createOrUpdateUser"),
                type: "POST",
                data : user,
                success : function(result){
                    debugger
                    $("#edit-popup").modal('close');
                    $("#success").modal();
                    UserManage.initTable();
                },
                error : function(e){
                    debugger
                    $("#edit-popup").modal('close');
                    $("#failed").modal();
                }
            })
        });
    })
</script>
