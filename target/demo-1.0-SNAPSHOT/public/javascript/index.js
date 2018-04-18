var index = $("#usermanage_node");
var chatroom = $("#usermanage_node");
var Index = {
		init : function(){
			$("#usermanage_node").on("click",function(){
				userManage.index();
			});
			
			$("#usermanage_node").on("click",function(){
				$.ajax({
					url : webDemo.formatUrl("userManage/index"),
					type : "GET",
					success : function(result){
						$(this).parentElement.addClass("am-active");
						index.removeClass("am-active");
						$("#main_content").html(result);
					},
					error : function(e){
						
					}
				});
			});
		},
		
		index : function() {
			$.ajax({
				url : webDemo.formatUrl("/userManage/index"),
				type : "GET",
				success : function(result){
					$(this).addClass(".am-active");
					chatroom.removeClass(".am-active");
					$("#main_content").html(result);
				},
				error : function(e){
					
				}
			});
		}
};