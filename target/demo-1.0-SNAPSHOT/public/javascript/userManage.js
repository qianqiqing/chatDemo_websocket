var UserManage = {
    init : function(){
        $("#createUser").on("click",function(){

        });
    },

    initTable : function(){
        // var name = $("#user_name").val();
        // var type = $("#onLine_type").val();
        $.ajax({
            url : webDemo.formatUrl(""),
            data : {
                name : name,
                type : type
            },
            success : function(result){
                $("#userBody").html(result);
            },
            error : function(e){
                $.dialog().error("加载失败");
            }
        });
    }
}