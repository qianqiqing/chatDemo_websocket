var GroupManage = {
    init : function(){

    },

    initTree : function(){
        $.ajax({
            url : webDemo.formatUrl("/groupManage/initTree"),
            data : {},
            success : function(result){
                $("#tree").html(result);
            },
            error : function(e){

            }
        });
    },

    initUserList : function(){
        $.ajax({
            url : webDemo.formatUrl("/userManage/queryList"),
            data : {
                name : name,
                status : status,
                type : "group"
            },
            success : function(result){
                $("#assignBody").html(result);
            },
            error : function(e){
                $.dialog().error("加载失败");
            }
        });
    }
}