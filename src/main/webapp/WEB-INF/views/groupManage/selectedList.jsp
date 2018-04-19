<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fieldset id="userFieldList">
    <legend>用户列表</legend>
    <div id="" class="am-panel-bd">
        <div class="am-g">
            <div class="am-u-md-6 am-cf">
                <div class="am-fl am-cf">
                    <div class="am-btn-toolbar am-fl">
                        <div class="am-btn-group am-btn-group-xs">
                            <button onclick="assignUser()" type="button" class="am-btn am-btn-default">
                                <span class="am-icon-plus"></span> 分配用户</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="am-u-sm-12">
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="table-check"><input id="all" onclick="selectAll()" type="checkbox" /></th>
                        <th class="table-title">用户名</th>
                        <th class="table-title">是否在线</th>
                        <th class="table-title">用户权限</th>
                        <th class="table-title">手机号</th>
                        <th class="table-title">邮箱</th>
                    </tr>
                    </thead>
                    <tbody id="selectUserList">
                    <c:if test="${!empty userList}">
                        <c:forEach var="user" items="${userList}">
                            <tr>
                                <td><input name="group_selected" type="checkbox" value="${user.id}" /></td>
                                <td>${user.name}</td>
                                <td>
                                    <c:if test="${user.status == 1}"><font color="green">在线</font></c:if>
                                    <c:if test="${user.status == 0}"><font color="red">离线</font></c:if>
                                </td>
                                <td>
                                    <c:if test="${user.role == 1}">管理员</c:if>
                                    <c:if test="${user.role == 0}">普通用户</c:if>
                                </td>
                                <td>${user.phone}</td>
                                <td>${user.email}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</fieldset>
<script>
    function selectAll(){
        $("input[name='group_selected']").prop("checked",$("#all").prop("checked"));
    }

    function assignUser(){
        var groupId = $("#groupId").val();
        debugger
        var ids = [];
        $('input[name="group_selected"]:checked').each(function(){
            ids.push($(this).val());
        });
        if(ids.length > 0){
            $.ajax({
                url : webDemo.formatUrl("/groupManage/assignUser/"+groupId+""),
                type : "POST",
                data : JSON.stringify(ids),
                contentType : "application/json",
                success : function(result){
                    GroupManage.initTree();
                },
                error : function(e){

                }
            });
        }else{
            $("#selectTip").modal();
        }
    }
</script>

<%--<button type="button" class="am-btn am-btn-primary" onclick="addUser()">分配用户</button>--%>
