<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqiqing
  Date: 2018/4/18
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="height: 100%">
    <fieldset>
        <legend>详情</legend>
        <form class="am-form am-form-horizontal" style="padding-bottom: 5px">
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
                        <option value="0" <c:if test="${user.role == 0}">selected</c:if>>普通用户</option>
                    </select>
                </div>
            </div>
        </form>
    </fieldset>
</div>


