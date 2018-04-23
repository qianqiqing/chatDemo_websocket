<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>在线聊天系统</title>
    <link rel="icon" type="image/png" href="${ctx}/public/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="${ctx}/public/assets/i/app-icon72x72@2x.png">
    <link rel="stylesheet" href="${ctx}/public/assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${ctx}/public/assets/css/admin.css">

</head>
<body>
<%--头部--%>
<header class="am-topbar admin-header">
    <div class="am-topbar-brand">
        <strong>ChatRoom</strong> <small>在线聊天系统</small>
    </div>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    <span class="am-icon-users"></span> ${currentUser.name} <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a id="logout" href="#"><span class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
        </ul>
    </div>
</header>
<%--主体部分--%>
<div class="am-cf admin-main">
    <!-- sidebar start -->
    <div class="admin-sidebar">
        <ul class="am-list admin-sidebar-list">
            <li><a id="baseInfo" href="#"><span class="am-icon-home"></span> 个人资料</a></li>
            <c:if test="${currentUser.role == 1}">
                <li><a id="userList" href="#"><span class="am-icon-table"></span> 用户列表</a></li>
                <li><a id="userGroup" href="#"><span class="am-icon-th"></span> 分组管理</a></li>
            </c:if>
            <li><a id="chatroom" href="#"><span class="am-icon-pencil-square-o"></span> 聊天室</a></li>
        </ul>
    </div>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">

    </div>
    <!-- content end -->
</div>
<%--弹出框--%>
<div class="am-modal am-modal-alert" tabindex="-1" id="success">
    <div class="am-modal-dialog">
        <div class="am-modal-hd"><span>操作成功！</span></div>
        <div class="am-modal-footer">
            <span class="am-modal-btn">确定</span>
        </div>
    </div>
</div>
<div class="am-modal am-modal-alert" tabindex="-1" id="failed">
    <div class="am-modal-dialog">
        <div class="am-modal-hd"><span>操作失败！</span></div>
        <div class="am-modal-footer">
            <span class="am-modal-btn">确定</span>
        </div>
    </div>
</div>
<div class="am-modal am-modal-alert" tabindex="-1" id="fileTip">
    <div class="am-modal-dialog">
        <div class="am-modal-hd"><span>文件大小不能超过2M！</span></div>
        <div class="am-modal-footer">
            <span class="am-modal-btn">确定</span>
        </div>
    </div>
</div>
<div class="am-modal am-modal-alert" tabindex="-1" id="alertTip">
    <div class="am-modal-dialog">
        <div class="am-modal-hd"><span></span></div>
        <div class="am-modal-footer">
            <span class="am-modal-btn">确定</span>
        </div>
    </div>
</div>

<footer>
    <hr>
    <p class="am-padding-left"></p>
</footer>
<script src="${ctx}/public/assets/js/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="${ctx}/public/assets/js/polyfill/rem.min.js"></script>
<script src="${ctx}/public/assets/js/polyfill/respond.min.js"></script>
<script src="${ctx}/public/assets/js/amazeui.legacy.js"></script>

<script src="${ctx}/public/assets/js/amazeui.min.js"></script>
<script src="${ctx}/public/assets/js/app.js"></script>
<script>
    $(function(){
        initIndex();
        $("#baseInfo").on("click",function(){
            initIndex();
        });

        $("#userList").on("click",function(){
            doAjax("/userManage/userListIndex","");
        });

        $("#userGroup").on("click",function(){
            doAjax("/groupManage/index","");
        });

        $("#chatroom").on("click",function(){
            doAjax("/chat/chatRoomIndex","");
        });

        $("#logout").on("click",function(){
            window.location.href = webDemo.formatUrl("/login/logOut");
        })
    })
    var path = "${ctx}";
    webDemo = {
        webSocket : null,
        formatUrl: function (url) {
            return path + url;
        }
    };

    function initIndex(){
        debugger
        var id = "${currentUser.id}";
        $.ajax({
            url : webDemo.formatUrl("/userManage/baseInfoIndex"),
            type : "GET",
            data : {
                id : id
            },
            success : function(result){
                $(".admin-main .admin-content").html(result);
            },
            error : function(e){

            }
        });
    }

    function doAjax(url,param){
        $.ajax({
            url : webDemo.formatUrl(url),
            type : "GET",
            data : {},
            success : function(result){
                $(".admin-main .admin-content").html(result);
            },
            error : function(e){

            }
        });
    }
</script>
</body>
</html>
