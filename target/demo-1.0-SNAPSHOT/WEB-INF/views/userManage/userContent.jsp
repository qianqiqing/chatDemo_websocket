<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${userList != null && userList.size>0}">
    <c:forEach items="userList" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>${user.status}</td>
            <td>
                <button class="am-btn am-btn-primary am-btn-xs">删除</button>
            </td>
        </tr>
    </c:forEach>
