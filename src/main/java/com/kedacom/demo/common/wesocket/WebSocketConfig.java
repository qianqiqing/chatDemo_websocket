package com.kedacom.demo.common.wesocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * webSocket配置类
 * @author 钱其清
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    /**
     * //注册webSocket的实现类
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(ChatRoom(), "/chat").addInterceptors(handshakeInterceptor());
    }

    /**
     * 实例化HandshakeInterceptor接口
     * @return
     */
    @Bean
    public HandshakeInterceptor handshakeInterceptor() {
        return new HandshakeInterceptor();
    }

    /**
     * 实例化ChatServer接口
     * @return
     */
    @Bean
    public ChatServer ChatRoom() {
        return new ChatServer();
    }

}
