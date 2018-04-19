<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!empty userList}">
    <c:forEach var="user" items="${userList}">
        <tr>
            <td><input type="checkbox" value="${user.id}" /></td>
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
            <c:if test="${type == 'user'}">
                <td>
                    <div class="am-btn-toolbar">
                        <div class="am-btn-group am-btn-group-xs">
                            <button onclick="edit_user('${user.id}')" class="am-btn am-btn-default am-btn-xs am-text-secondary" data-path="${user.id}"><span class="am-icon-pencil-square-o"></span> 编辑</button>
                            <button onclick="del_user('${user.id}')" class="am-btn am-btn-default am-btn-xs am-text-danger" data-path="${user.id}"><span class="am-icon-trash-o"></span> 删除</button>
                        </div>
                    </div>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</c:if>
<script type="application/javascript">
    function edit_user(id){
        debugger
        $.ajax({
            url : webDemo.formatUrl("/userManage/editUserIndex"),
            data : {
                userId : id
            },
            success : function(result){
                $("#edit-popup").html(result);
                $("#edit-popup").modal();
            },
            error : function(e){
                $.dialog().error("加载失败");
            }
        })
    }

    function del_user(id){
        $.ajax({
            url : webDemo.formatUrl("/userManage/deleteUser"),
            type : "post",
            data : {
                userId : id
            },
            success : function(result){
                $("#success").modal();
                UserManage.initTable();
            },
            error : function(e){
                $("#failed").modal();
            }
        })
    }
</script>
