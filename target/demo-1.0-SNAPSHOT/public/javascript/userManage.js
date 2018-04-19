var UserManage = {
    init : function(){
        $("#create_user").on("click",function(){
            $.ajax({
                url : webDemo.formatUrl("/userManage/createUserIndex"),
                data : {},
                success : function(result){
                    $("#edit-popup").html(result);
                    $("#edit-popup").modal();
                },
                error : function(e){

                }
            });
        });

        $("#searchUser").on("click",function(){
            UserManage.initTable();
        });
    },

    initTable : function(type){
        debugger
        var name = $("#query_user_name").val();
        var status = $("#query_online_type").val();
        $.ajax({
            url : webDemo.formatUrl("/userManage/queryList"),
            data : {
                name : name,
                status : status,
                type : "user"
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