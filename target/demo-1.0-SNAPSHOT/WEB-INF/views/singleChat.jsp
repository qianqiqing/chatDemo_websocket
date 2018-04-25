<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String path = request.getContextPath();%>
<script src="${path}/demo/public/javascript/common/reconnecting-websocket.min.js"></script><%--<jsp:include page="common/commonfile.jsp"/>--%>
<!-- content start -->
<html>
<div class="am-cf am-padding">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">聊天室</strong> / <small>Chat Room</small></div>
</div>
<div class="admin-content">
    <div class="" style="width: 80%;float:left;">
        <!--中间聊天部分  -->
        <section class="am-panel am-panel-default">
            <!-- 聊天区 -->
            <div class="am-scrollable-vertical" id="chat-view" style="height: 70%;">
                <ul class="am-comments-list" id="chat">
                </ul>
            </div>
            <!-- 输入区 -->
            <div class="am-form-group am-form" >
                <textarea class="" id="message" name="message" rows="5"  placeholder="发送给全体成员..."></textarea>
            </div>
            <div id="progress">
            </div>
            <%--预览区--%>
            <div class="" style="float:left;">
                <input type="file" id="file">
            </div>
            <!-- 接收者 -->
            <div class="" style="display: none">
                <span id="sendto">全体成员</span>
            </div>
            <!-- 按钮区 -->
            <div class="am-btn-group am-btn-group-xs" style="float:right;">
                <button class="am-btn am-btn-default" type="button" onclick="sendMessage()"><span class="am-icon-commenting"></span> 发送文字</button>
                <button class="am-btn am-btn-default" type="file" onclick="sendImage('image')"><span class="am-icon-file-image-o"></span> 发送图片</button>
                <button class="am-btn am-btn-default" type="file" onclick="sendFile('file')"><span class="am-icon-file-image-o"></span> 发送文件</button>
                <button class="am-btn am-btn-default" type="button" onclick="clearConsole()"><span class="am-icon-trash-o"></span> 清屏</button>
            </div>
        </section>
    </div>

    <!-- 列表区 -->
    <div class="am-panel am-panel-default" style="float:right;width: 20%;">
        <div class="am-panel-hd">
            <h3 class="am-panel-title">在线列表 [<span id="onlinenum">${size}</span>]<button style="float: right" onclick="addChat('全体成员')" class="am-btn am-btn-xs am-btn-primary am-round"><span class="am-icon-phone"></span> 群聊</button></h3>
        </div>
        <ul class="am-list am-list-static am-list-striped" id="onLineUserlist">
            <c:forEach var="user" items="${onLineUser}">
               <li>${user.name}
                   <button type="button" style="float: right" class="am-btn am-btn-xs am-btn-primary am-round" onclick="addChat('${user.name}') ">
                       <span class="am-icon-phone">私聊 </span>
                   </button>
               </li>
            </c:forEach>
        </ul>
    </div>
</div>
</html>
<!-- content end -->
<script>
    // 使用 ReconnectingWebSocket连接websocket,可实现断线重连
    if(webDemo.webSocket == null){
        webDemo.webSocket = new  ReconnectingWebSocket ("ws://" + location.host+"${pageContext.request.contextPath}" + "/chat");
    }
    var webSocket = webDemo.webSocket;
    <%--var webSocket = new  ReconnectingWebSocket ("ws://" + location.host+"${pageContext.request.contextPath}" + "/chat");--%>

    //连接成功建立的回调方法
    webSocket.onopen = function (event) {
        setMessageInnerHTML("系统消息：加入连接");
    };
    webSocket.onmessage = function (evt) {
        analysisMessage(evt.data);  //解析后台传回的消息,并予以展示
    };
    //连接关闭的回调方法
    webSocket.onclose = function () {
        setMessageInnerHTML("系统消息：断开连接");
    };
    //连接发生错误的回调方法
    webSocket.onerror = function () {
        setMessageInnerHTML("系统消息：error");
    };
    /**
     *监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，
     *防止连接还没断开就关闭窗口，server端会抛异常。
     */
    window.onbeforeunload = function(event) {
        debugger
        //判断是否是关闭窗口
        if(event.clientX>document.body.clientWidth&&event.clientY<0||event.altKey)
        {
            console.log("关闭WebSocket连接！");
            webSocket.close();
        }
    }
    function closeWebSocket() {
        webSocket.close();
    }
    /**
     * 发送信息给后台
     */
    function sendMessage(){
        // $("#progress").html("");
        var message = $("#message").val();
        var to = $("#sendto").text() == "全体成员"? "": $("#sendto").text();
        var obj = {
            message : message,//输入框的内容
            from : '${currentUser.name}',//登录成功后保存在Session.attribute中的username
            to : to,      //接收人,如果没有则置空,如果有多个接收人则用,分隔
            messageType : "text",
            filePath : ""
        };
        webSocket.send(JSON.stringify(obj));
    }
    /*
    * 发送文件给后台
    * */
    function sendImage(type){
        var to = $("#sendto").text() == "全体成员"? "": $("#sendto").text();
        var img = document.getElementById('file');
        var file = img.files[0];
        var fileSize = file.size / 1024;
        if(fileSize>2000){
            $("#fileSizeTip").modal();
        }else{
            var formData = new FormData();
            formData.append("file",file);
            $.ajax({
                url : webDemo.formatUrl("/fileLoad/uploadImage"),
                type: "POST",
                data : formData,
                processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                contentType : false,  //必须false才会自动加上正确的Content-Type,
                success : function(result){
                    debugger
                    if(result == "文件超过2M"){
                        $("#fileSizeTip").modal();
                    } else if(result == "文件格式不正确"){
                        $("#fileTypeTip").modal();
                    }else{
                        var obj = {
                            message : file.name,//输入框的内容
                            from : '${currentUser.name}',//登录成功后保存在Session.attribute中的username
                            to : to,      //接收人,如果没有则置空,如果有多个接收人则用,分隔
                            messageType : type,
                            filePath : result
                        };
                        webSocket.send(JSON.stringify(obj));
                    }

                },
                error : function(e){
                }
            });
        }
    }
    /**
     * 发送文件给后台
     */
    function sendFile(type){
        var $progress = "<div class=\"am-progress am-progress-striped am-progress-sm am-active \" style=\"width: 85%;float: left\">\n" +
            "                <div id=\"progressStyle\" class=\"am-progress-bar am-progress-bar-secondary\"></div>\n" +
            "                </div>\n" +
            "                <div style=\"float: right;\"><span id=\"progressNum\"></span></div>";
        $("#progress").html($progress);
        debugger
        var to = $("#sendto").text() == "全体成员"? "": $("#sendto").text();
        var img = document.getElementById('file');
        var file = img.files[0];
        var fileSize = file.size / 1024;
        if(fileSize>5000*1000){
            $("#fileSizeTip").modal();
        }else{
            var formData = new FormData();
            formData.append("file",file);
            $.ajax({
                url : webDemo.formatUrl("/fileLoad/uploadImage"),
                type: "POST",
                data : formData,
                processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
                contentType : false,  //必须false才会自动加上正确的Content-Type,
                xhr: function(){
                    myXhr = $.ajaxSettings.xhr();
                    if(myXhr.upload){
                        myXhr.upload.addEventListener('progress',function(e) {
                            if (e.lengthComputable) {
                                var percent = Math.floor(e.loaded/e.total*100);
                                if(percent <= 100) {
                                    document.getElementById('progressStyle').style.width = percent+'%';
                                    $("#progressNum").html('已上传：'+percent+'%');
                                }
                                if(percent >= 100) {
                                    $("#progressNum").html('上传成功');
                                }
                            }
                        }, false);
                    }
                    return myXhr;
                },
                success : function(result){
                    debugger
                    if(result == "文件超过2M"){
                        $("#fileSizeTip").modal();
                    } else if(result == "文件格式不正确"){
                        $("#fileTypeTip").modal();
                    }else{
                        var obj = {
                            message : file.name,//输入框的内容
                            from : '${currentUser.name}',//登录成功后保存在Session.attribute中的username
                            to : to,      //接收人,如果没有则置空,如果有多个接收人则用,分隔
                            messageType : type,
                            filePath : result
                        };
                        webSocket.send(JSON.stringify(obj));
                    }

                },
                error : function(e){
                }
            });
        }
    }
    <%--function sendFileOrImage(type){--%>
        <%--var $progress = "<div class=\"am-progress am-progress-striped am-progress-sm am-active \" style=\"width: 85%;float: left\">\n" +--%>
            <%--"                <div id=\"progressStyle\" class=\"am-progress-bar am-progress-bar-secondary\"></div>\n" +--%>
            <%--"                </div>\n" +--%>
            <%--"                <div style=\"float: right;\"><span id=\"progressNum\"></span></div>";--%>
        <%--$("#progress").html($progress);--%>
        <%--debugger--%>
        <%--var to = $("#sendto").text() == "全体成员"? "": $("#sendto").text();--%>
        <%--var img = document.getElementById('file');--%>
        <%--var file = img.files[0];--%>
        <%--var fileSize = file.size / 1024;--%>
        <%--if(fileSize>5000*1000){--%>
            <%--$("#fileSizeTip").modal();--%>
        <%--}else{--%>
            <%--var formData = new FormData();--%>
            <%--formData.append("file",file);--%>
            <%--$.ajax({--%>
                <%--url : webDemo.formatUrl("/fileLoad/uploadImage"),--%>
                <%--type: "POST",--%>
                <%--data : formData,--%>
                <%--processData : false,  //必须false才会避开jQuery对 formdata 的默认处理--%>
                <%--contentType : false,  //必须false才会自动加上正确的Content-Type,--%>
                <%--xhr: function(){--%>
                    <%--myXhr = $.ajaxSettings.xhr();--%>
                    <%--if(myXhr.upload){--%>
                        <%--myXhr.upload.addEventListener('progress',function(e) {--%>
                            <%--if (e.lengthComputable) {--%>
                                <%--var percent = Math.floor(e.loaded/e.total*100);--%>
                                <%--if(percent <= 100) {--%>
                                    <%--document.getElementById('progressStyle').style.width = percent+'%';--%>
                                    <%--$("#progressNum").html('已上传：'+percent+'%');--%>
                                <%--}--%>
                                <%--if(percent >= 100) {--%>
                                    <%--$("#progressNum").html('上传成功');--%>
                                <%--}--%>
                            <%--}--%>
                        <%--}, false);--%>
                    <%--}--%>
                    <%--return myXhr;--%>
                <%--},--%>
                <%--success : function(result){--%>
                    <%--debugger--%>
                    <%--if(result == "文件超过2M"){--%>
                        <%--$("#fileSizeTip").modal();--%>
                    <%--} else if(result == "文件格式不正确"){--%>
                        <%--$("#fileTypeTip").modal();--%>
                    <%--}else{--%>
                        <%--var obj = {--%>
                            <%--message : file.name,//输入框的内容--%>
                            <%--from : '${currentUser.name}',//登录成功后保存在Session.attribute中的username--%>
                            <%--to : to,      //接收人,如果没有则置空,如果有多个接收人则用,分隔--%>
                            <%--messageType : type,--%>
                            <%--filePath : result--%>
                        <%--};--%>
                        <%--webSocket.send(JSON.stringify(obj));--%>
                    <%--}--%>

                <%--},--%>
                <%--error : function(e){--%>
                <%--}--%>
            <%--});--%>
        <%--}--%>
    <%--}--%>

    /*
    * 解析后台传来的消息
    */
    function analysisMessage(message){
        debugger
        message = JSON.parse(message);
        if(message.messageType == "text"){      //会话消息
            showChat(message);
        }
        if(message.messageType == "file"){         //文件信息
            showFile(message);
        }
        if(message.messageType == "image"){
            showImage(message);
        }
        if(message.type == "notice"){       //提示消息
            showNotice(message.message);
        }
        if(message.list != null && message.list != undefined){      //在线列表
            showOnline(message.list);
        }
    }
    /**
     * 展示会话信息
     */
    function showChat(message){
        var to = $("#sendto").text();
        var html = "<font color='#a52a2a'><font color='green'>"+ message.from+"</font> ("+getNowFormatDate()+") 对 <font color='green'>" + to + "</font> 说:</font></br>"+message.message+"</br></br>";
        $("#chat").append(html);
        $("#message").val("");  //清空输入区
        var chat = $("#chat-view");
        chat.scrollTop(chat[0].scrollHeight);   //让聊天区始终滚动到最下面
    }
    /**
     * 展示提示信息
     */
    function showNotice(notice){
        debugger
        var sendTo = $("#sendto").text();
        var obj = notice.split("]");
        if("离开了聊天室" == obj[1]){
            if(obj[0].replace("[","") == sendTo){
                addChat("全体成员");
            }
        }
        $("#chat").append("<div><p class=\"am-text-success\" style=\"text-align:center\"><span class=\"am-icon-bell\"></span> "+notice+"</p></div>");
        var chat = $("#chat-view");
        chat.scrollTop(chat[0].scrollHeight);   //让聊天区始终滚动到最下面
    }
    /*
    * 展示文件信息
    * */
    function showFile(messageJson){
        var to = $("#sendto").text();
        var filePath = messageJson.filePath.replace(/\\/g,",");
        var html = "<font color='#a52a2a'><font color='green'>"+messageJson.from+"</font> ("+getNowFormatDate()+") 给 <font color='green'>" +to+ "</font> 发送文件:</font></br> <a href=\"#\" onclick=\"downLoad('"+filePath+"')\">"+messageJson.message+"</a></br></br>";
        $("#chat").append(html);
        var chat = $("#chat-view");
        chat.scrollTop(chat[0].scrollHeight);
    }
    /**
     * 展示图片信息
     */
    function showImage(messageJson) {
        debugger;
        var to = $("#sendto").text();
        var imagePath = decodeURI(messageJson.filePath).replace(/,/g,"\/");
        var html = "<font color='#a52a2a'><font color='green'>"+messageJson.from+"</font> ("+getNowFormatDate()+") 对 <font color='green'>" +to+ "</font> 说: </font></br>";
        var img = document.createElement("img");
        img.src = "${ctx}/demo/"+imagePath+"";
        $("#chat").append(html).append(img).append("</br></br>");
        var chat = $("#chat-view");
        chat.scrollTop(chat[0].scrollHeight);   //让聊天区始终滚动到最下面
    }
    /**
     * 展示在线列表
     */
    function showOnline(list){
        debugger
        var obj = [];
        $("#onLineUserlist").html("");    //清空在线列表
        $.each(list, function(index, item){     //添加私聊按钮
            debugger
            if($.inArray(item,obj) < 0){
                var li = "<li>"+item+"</li>";
                if('${currentUser.name}' != item){    //排除自己
                    li = "<li>"+item+" <button type=\"button\" style=\"float: right\" class=\"am-btn am-btn-xs am-btn-primary am-round\" onclick=\"addChat('"+item+"') \"><span class=\"am-icon-phone\"><span> 私聊</button></li>";
                }
                $("#onLineUserlist").append(li);
                obj.push(item);
            }
        });
        $("#onlinenum").text($("#onLineUserlist li").length);     //获取在线人数
    }
    /**
     * 添加接收人
     */
    function addChat(user){
        debugger
        var sendto = $("#sendto");
        var receive = sendto.text();
        if(user == "全体成员"){
            receive = "全体成员";
        }else {
            if(receive!="全体成员"){
                receive += ","+user;
            }else{
                receive = user;
            }
        }
        sendto.text(receive);
        $('textarea').attr('placeholder',"发送给 "+user+"");
    }
    /**
     * 清空聊天区
     */
    function clearConsole(){
        $("#chat").html("");
    }
    /**
     * 发送系统消息
     * @param innerHTML
     */
    function setMessageInnerHTML(innerHTML) {
        $("#chat").append(innerHTML+"<br/>")
    };
    /**
     * 下载
     */
    function downLoad(filePath){
        debugger
        filePath = filePath.replace(/,/g,"/");
        window.location.href = "<%=path%>/fileLoad/downLoad?filePath=" + encodeURI(encodeURI(filePath));
    };
    /*
    * 获取当前时间
    * */
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
        return currentdate;
    }
</script>
</body>
</html>
