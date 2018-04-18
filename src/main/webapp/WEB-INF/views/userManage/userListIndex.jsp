<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${ctx}/demo/public/javascript/userManage.js"></script>
<html>
<div class="am-cf am-padding">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户列表</strong> / <small>User List</small></div>
</div>
    <div class="am-g">
        <div class="am-u-md-6 am-cf">
            <div class="am-fl am-cf">
                <div class="am-btn-toolbar am-fl">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
                        <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>
                        <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 审核</button>
                        <button type="button" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button>
                    </div>

                    <div class="am-form-group am-margin-left am-fl">
                        <select>
                            <option value="option1">所有类别</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-u-md-3 am-cf">
            <div class="am-fr">
                <div class="am-input-group am-input-group-sm">
                    <input type="text" class="am-form-field">
                    <span class="am-input-group-btn">
                  <button class="am-btn am-btn-default" type="button">搜索</button>
                </span>
                </div>
            </div>
        </div>
    </div>
    <div class="am-g">
        <div class="am-u-sm-12">
            <form class="am-form">
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                        <tr>
                            <th class="table-title">用户名</th>
                            <th class="table-type">是否在线</th>
                            <th class="table-author">用户权限</th>
                            <th class="table-date">邮箱</th>
                            <th class="table-set">操作</th>
                        </tr>
                    </thead>
                    <tbody id="userBody">

                    </tbody>
                </table>
            </form>
        </div>
    </div>
</html>
<script>
     UserManage.init();

</script>