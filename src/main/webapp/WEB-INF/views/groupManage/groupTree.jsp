<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${ctx}/demo/public/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/demo/public/css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/demo/public/css/demo.css">
<script src="${ctx}/demo/public/javascript/common/jquery.min.js"></script>
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
    var tree = $("#tt");

    function treeClick(node) {
        debugger
        var url;
        if(node.type != null && node.type != ""){
            if(node.type == "group"){
                url = "/groupManage/groupDetail";
            }else{
                url = "/userManage/baseInfoIndex";
            }
        }
        $.ajax({
            url : webDemo.formatUrl(url),
            data : {
                id : node.id
            },
            success : function(result){
                $("#groupDetail").html(result);
            },
            error : function(e){

            }
        });
    }

    function append(){
        var t = $('#tt');
        var node = t.tree('getSelected');
        t.tree('append', {
            parent: (node?node.target:null),
            data: [{
                text: 'new item1'
            },{
                text: 'new item2'
            }]
        });
    }
    function removeit(){
        var node = $('#tt').tree('getSelected');
        $('#tt').tree('remove', node.target);
    }
    function collapse(){
        var node = $('#tt').tree('getSelected');
        $('#tt').tree('collapse',node.target);
    }
    function expand(){
        var node = $('#tt').tree('getSelected');
        $('#tt').tree('expand',node.target);
    }
</script>
