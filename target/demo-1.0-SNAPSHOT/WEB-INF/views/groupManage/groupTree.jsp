<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${ctx}/demo/public/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/demo/public/css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/demo/public/css/demo.css">
<script type="text/javascript" src="${ctx}/demo/public/javascript/common/jquery.easyui.min.js"></script>
<div class="">
    <ul id="tt" class="easyui-tree" data-options="
				url: '${ctx}/demo/groupManage/treeData',
				method: 'get',
				animate: true,
				onClick: function(node){
					treeClick(node);
				},
				onContextMenu: function(e,node){
					e.preventDefault();
					$(this).tree('select',node.target);
					$('#mm').menu('show',{
						left: e.pageX,
						top: e.pageY
					});
				}
			"></ul>
</div>
<div id="mm" class="easyui-menu" style="width:120px;display: none">
    <div onclick="append()" data-options="iconCls:'icon-add'">新增</div>
    <div onclick="removeit()" data-options="iconCls:'icon-remove'">删除</div>
</div>
<script type="text/javascript">
    //节点点击事件
    function treeClick(node) {
        if(node.id != null){
            groupDetail(node);
            if(node.type == "group"){
                selectUserList(node);
            }
        }
    }

    //创建分组
    function append(){
        var t = $('#tt');
        var node = t.tree('getSelected');
        $.ajax({
            url : webDemo.formatUrl("/groupManage/groupDetail"),
            data : {
                id : node.id,
                type : "create"
            },
            success : function(result){
                $("#groupDetail").html(result);
                $("#userFieldList").remove();
            },
            error : function(e){

            }
        });
    }

    //删除节点
    function removeit(){
        var node = $('#tt').tree('getSelected');
        $.ajax({
            url : webDemo.formatUrl("/groupManage/deleteGroup"),
            type : "POST",
            data : {
                id : node.id
            },
            success : function(result){
                GroupManage.initTree();
            },
            error : function(e){

            }
        });
    }

    function groupDetail(node){
        var url;
        var data;
        if(node.type != null && node.type != ""){
            if(node.type == "group"){
                url = "/groupManage/groupDetail";
                data = {id : node.id,type : "edit"};
            }else{
                url = "/groupManage/userDetail";
                data = {id : node.id}
            }
        }
        $.ajax({
            url : webDemo.formatUrl(url),
            data : data,
            success : function(result){
                $("#groupDetail").html(result);

            },
            error : function(e){

            }
        });
    }

    function selectUserList(node){
        $.ajax({
            url : webDemo.formatUrl("/groupManage/selectListIndex"),
            data : {},
            success : function(result){
                $("#userField").html(result);
            },
            error : function(e){

            }
        });
    }

</script>
