<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/public/css/amazeui.min.css">
<link rel="stylesheet" href="${ctx}/public/css/amazeui.css">
<script src="${ctx}/public/javascript/common/jquery.min.js"></script>
<script src="${ctx}/public/assets/js/amazeui.min.js"></script>

<script type="text/javascript">
    var path = '${ctx}';
	webDemo = {
	    formatUrl: function (url) {
	        return path + url;
	    }
	};
</script>
    
