<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="${ctx}/demo/public/javascript/groupManage.js"></script>

<div class="am-cf am-padding">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户分组</strong> / <small>User List</small></div>
</div>
<div id="groupIndex">
    <div style="width: 20%;float: left">
        <section class="am-panel am-panel-default">
            <div id="tree" class="am-panel-bd" style="height: 100%">

            </div>
        </section>
    </div>

    <div style="width: 78%; float: right">
        <section id="groupContent" class="am-panel am-panel-default">
        </section>

    </div>
</div>

<div class="am-modal am-modal-alert" tabindex="-1" id="selectTip">
    <div class="am-modal-dialog">
        <div class="am-modal-hd"><span>请选择至少一条数据</span></div>
        <div class="am-modal-footer">
            <span class="am-modal-btn">确定</span>
        </div>
    </div>
</div>

<%--<jsp:include page="../common/dialog.jsp" />--%>
<script type="application/javascript">
     GroupManage.initTree();


</script>
