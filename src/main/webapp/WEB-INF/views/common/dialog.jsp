<%--
  Created by IntelliJ IDEA.
  User: qianqiqing
  Date: 2018/4/19
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${ctx}/demo/public/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/demo/public/css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/demo/public/css/demo.css">
<script type="text/javascript" src="${ctx}/demo/public/javascript/common/jquery.easyui.min.js"></script>

<div id="dialog" closed="true" class="easyui-dialog" title="" style="width:800px;height:600px;padding:10px"
     data-options="
				iconCls: 'icon-save',
				buttons: '#dlg-buttons'
			">
    <section id="dialogContent">


    </section>
</div>

<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:alert('save')">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dialog').dialog('close')">Close</a>
</div>
