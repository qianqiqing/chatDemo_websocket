<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head lang="en">
    <title>Login Page</title>
    <jsp:include page="../common/commonfile.jsp"/>
    <style>
        .header {
            text-align: center;
        }
        .header h1 {
            font-size: 200%;
            color: #333;
            margin-top: 30px;
        }
        .header p {
            font-size: 14px;
        }
    </style>
</head>
<body>
<div class="header">
    <div class="am-g">
        <h1>在线聊天</h1>
    </div>
    <hr />
</div>
<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <h3>用户登录</h3>
        <br>
        <br>

        <form action="<%=path%>/login/loginValidate" method="post" class="am-form">
            <%--@declare id="username"--%><label for="username">用户名:</label>
            <input type="text" name="name" id="name" value="">
            <br>
            <label for="password">密码:</label>
            <input type="password" name="password" id="password" value="">
                <br>
            <div class="am-cf">
                <input type="submit" id="submit" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl">
            </div>
        </form>
        <hr>
    </div>
</div>
</body>
</html>