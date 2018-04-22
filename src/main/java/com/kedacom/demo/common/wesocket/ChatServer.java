package com.kedacom.demo.common.wesocket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kedacom.demo.common.enums.MessageTypeEnum;
import com.kedacom.demo.common.utils.ConstantDefine;
import com.kedacom.demo.common.utils.JsonUtil;
import com.kedacom.demo.model.ChatContent;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kedacom.demo.model.User;

/**
 * 继承AbstractWebSocketHandler实现webSocket通信
 * @author 钱其清
 *
 */
public class ChatServer extends AbstractWebSocketHandler {
    private Logger logger = Logger.getLogger(this.getClass());

    private final static List<WebSocketSession> sessionList = Collections.synchronizedList(new ArrayList<WebSocketSession>());
    private static List<String> list = new ArrayList();                        //在线列表
    private static Map<String,WebSocketSession> routetab = new HashMap();      //用户名和session的对应map

    private SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private FileOutputStream outPut;

    /**
     * 连接建立成功调用的方法,重写父类AbstractWebSocketHandler中的方法
     * @param webSocketSession
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        User currentUser = (User) webSocketSession.getAttributes().get("currentUser"); //获取当前用户
        sessionList.add(webSocketSession);
        TextMessage message;
        if (!list.contains(currentUser.getName())) {
            list.add(currentUser.getName());
            routetab.put(currentUser.getName(), webSocketSession);                               //将用户名和session绑定到路由表
            message = getMessage("[" + currentUser.getName() + "]加入聊天室", "notice",  list);
            broadcast(message);                                                      //广播通知
        } else {
            list.add(currentUser.getName());
        }
    }

    /**
     * 连接关闭调用的方法,重写父类AbstractWebSocketHandler中的方法
     * @param webSocketSession
     * @param status
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) throws Exception {
        User currentUser = (User) webSocketSession.getAttributes().get("currentUser"); //获取当前用户
        list.remove(currentUser.getName());
        sessionList.remove(webSocketSession);              //关闭连接将webSocketSession移除；如不移除导致广播的时候会用关闭的session发送消息，抛出异常
        TextMessage message;
        if (!list.contains(currentUser.getName())) {
            routetab.remove(currentUser.getName());
            message = getMessage("[" + currentUser.getName() +"]离开了聊天室", "notice", list);
            broadcast(message);
        }
    }

    /**
     * 处理客户端发送端的消息,重写父类AbstractWebSocketHandler中的方法
     * @description 客户端发送的是一个组装的json字符串，包含发送人，接收人，消息类型等
     * @param websocketsession
     * @param message
     */
    @Override
    public void handleTextMessage(WebSocketSession websocketsession, TextMessage message) {
        //消息的json字符串
        String payload = message.getPayload();
        //json字符串转成chatContent实体类
        ChatContent chatContent = JsonUtil.toObject(ChatContent.class,payload);
        try {
            if (StringUtils.isEmpty(chatContent.getTo())) {      //如果to为空，发送给所有人;如果不为空,则对指定的用户发送消息
                broadcast(message);
            } else {
                String [] userlist =chatContent.getTo().split(",");
                sendText(message, (WebSocketSession) routetab.get(chatContent.getFrom()));//发送给自己,这个别忘了
                for (String user : userlist) {
                    if (!user.equals(chatContent.getFrom())) {
                        sendText(message, (WebSocketSession) routetab.get(user));             //分别发送给每个指定用户
                    }
                }
            }
        } catch (Exception e) {
            logger.error("handleTextMessage exception:"+e);
        }
    }

    /**
     * 处理客户端发送的BinaryMessage,重写父类AbstractWebSocketHandler中的方法
     * @param session
     * @param message
     */
    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        ByteBuffer buffer = message.getPayload();
        try {
            outPut.write(buffer.array());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * webSocket消息传输异常处理,重写父类AbstractWebSocketHandler中的方法
     * @param webSocketSession
     * @param throwable
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        User currentUser = (User) webSocketSession.getAttributes().get("currentUser"); //获取当前用户
        list.remove(currentUser.getName());
        sessionList.remove(webSocketSession);       //关闭连接将webSocketSession移除；如不移除导致广播的时候会用关闭的session发送消息，抛出异常
    }

    /**
     * 支持分段发送,重写父类AbstractWebSocketHandler中的方法
     * @return [boolean] [设为true表示支持分段发送]
     */
    @Override
    public boolean supportsPartialMessages() {
        return true;
    }

    /**
     * 发送文本信息
     * @param message
     * @param session
     */
    private void sendText(TextMessage message, WebSocketSession session) {
        try {
            session.sendMessage(message);
        } catch (Exception e) {
            logger.error("发送消息失败:"+e);
        }
    }

    /**
     * 发送信息给所有人
     * @param _message
     */
    private void broadcast(TextMessage _message) {
        try {
            for (WebSocketSession session: sessionList) {
                session.sendMessage(_message);
            }
        } catch (IOException e) {
            logger.error("broadcast error :" + e);
        }
    }

    /**
     * 组装通知消息及在线列表
     * @param message
     * @param type
     * @param list
     * @return
     */
    private TextMessage getMessage(String message, String type, List<String> list) {
        JSONObject member = new JSONObject();
        member.put("message", message);
        member.put("type", type);
        member.put("list", list);
        TextMessage textMessage = new TextMessage(member.toString());
        return textMessage;
    }

//    /**
//     * 发送文件的二级制信息；
//     * @param fileName
//     * @param session
//     */
//    private TextMessage buildImageMessage(String fileName,String message, WebSocketSession session) {
//        FileInputStream input;
//        TextMessage textMessage = null;
//        JSONObject member = new JSONObject();
//        member.put("message",message);
//        try {
//            File file = new File(fileName);
//            input = new FileInputStream(file);
//            byte bytes[] = new byte[(int) file.length()];
//            input.read(bytes);
//            BinaryMessage byteMessage = new BinaryMessage(bytes);
//            member.put("blob",byteMessage);
//            textMessage = new TextMessage(member.toString());
//            input.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return textMessage;
//    }

}
