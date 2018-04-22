<%--
  Created by IntelliJ IDEA.
  User: qianqiqing
  Date: 2018/4/18
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    #vld-tooltip {
        position: absolute;
        z-index: 1000;
        padding: 5px 10px;
        background: #F37B1D;
        min-width: 150px;
        color: #fff;
        transition: all 0.15s;
        box-shadow: 0 0 5px rgba(0,0,0,.15);
        display: none;
    }

    #vld-tooltip:before {
        position: absolute;
        top: -8px;
        left: 50%;
        width: 0;
        height: 0;
        margin-left: -8px;
        content: "";
        border-width: 0 8px 8px;
        border-color: transparent transparent #F37B1D;
        border-style: none inset solid;
    }
</style>

<input type="hidden" id="userId" value="${user.id}" />
<div class="am-popup-inner" id="createCreate">
    <div class="am-popup-hd">
        <h4 class="am-popup-title">新增用户</h4>
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

            <button type="button" class="am-btn am-btn-primary am-btn-block" id="edit_save">创建用户</button>
        </form>
    </div>
</div>
<script type="application/javascript">
    $(function(){
        $("#edit_save").on("click",function(){
            var user= {
                "id" : null,
                "name" : $("#edit_user #name").val(),
                "email" : $("#edit_user #email").val(),
                "photo" : "",
                "password" : $("#password").val(),
                "group" : "",
                "status" : "",
                "phone" : $("#edit_user #phone").val(),
                "role" : $("#edit_user #role").val(),
            };
            if(validate(user)){
                $.ajax({
                    url : webDemo.formatUrl("/userManage/createOrUpdateUser"),
                    type: "POST",
                    data : user,
                    success : function(result){
                        if(result){
                            $("#alertTip .am-modal-hd span").html("用户名已存在");
                            $("#alertTip").modal();
                        }else{
                            $("#edit-popup").modal('close');
                            $("#success").modal();
                            UserManage.initTable();
                        }
                    },
                    error : function(e){
                        debugger
                        $("#edit-popup").modal('close');
                        $("#failed").modal();
                    }
                })
            }
        });
    });

    function validate(obj){
        var flag = true;
        if(obj.name == ""){
            $("#alertTip .am-modal-hd span").html("用户名不能为空");
            $("#alertTip").modal();
            flag = false;
        }else if(obj.password == ""){
            $("#alertTip .am-modal-hd span").html("密码不能为空");
            $("#alertTip").modal();
            flag = false;
        }else{
            if(obj.email != ""){
                var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
                if(!myreg.test(obj.email)){
                    $("#alertTip .am-modal-hd span").html("邮箱格式不正确");
                    $("#alertTip").modal();
                    flag = false;
                }
            }
        }
        return flag;
    }

</script>
