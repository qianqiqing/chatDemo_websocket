<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${ctx}/demo/public/javascript/userManage.js"></script>
<div class="am-cf am-padding">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户列表</strong> / <small>User List</small></div>
</div>
<div class="am-g">
    <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
            <div class="am-btn-toolbar am-fl">
                <div class="am-btn-group am-btn-group-xs">
                    <button type="button" class="am-btn am-btn-default" id="create_user"><span class="am-icon-plus"></span> 新增</button>
                </div>
            </div>
        </div>
    </div>
    <div class="am-u-md-3 am-cf">
        <div class="am-fr">
            <span>用户状态</span>
            <select id="query_online_type">
                <option value="">全部</option>
                <option value="1">在线</option>
                <option value="0" >离线</option>
            </select>
        </div>
    </div>
    <div class="am-u-md-3 am-cf">
        <div class="am-fr">
            <div class="am-input-group am-input-group-sm">
                <input type="text" id="query_user_name" class="am-form-field">
                <span class="am-input-group-btn">
                    <button id="searchUser" class="am-btn am-btn-default" type="button">搜索</button>
                </span>
            </div>
        </div>
    </div>
</div>
<div class="am-g">
    <div class="am-u-sm-12">
            <table class="am-table am-table-striped am-table-hover table-main">
                <thead>
                    <tr>
                        <th style="display:none;">id</th>
                        <th class="table-title">用户名</th>
                        <th class="table-title">是否在线</th>
                        <th class="table-title">用户权限</th>
                        <th class="table-title">手机号</th>
                        <th class="table-title">邮箱</th>
                        <th class="table-title">操作</th>
                    </tr>
                </thead>
                <tbody id="userBody">

                </tbody>
            </table>
    </div>
</div>

<div class="am-popup" id="edit-popup" style="width:600px;height:500px">

</div>
<script>
    UserManage.initTable();
    UserManage.init();
</script>