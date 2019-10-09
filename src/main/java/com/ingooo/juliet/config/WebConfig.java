package com.ingooo.juliet.config;

import com.ingooo.websocket.util.handler.WsSocketHandle;
import com.ingooo.websocket.util.listener.RequestListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Create by 丶TheEnd on 2019/10/9 0009.
 * @author Administrator
 */
@Configuration
public class WebConfig {

    @Autowired
    private RequestListener requestListener;

    @Bean
    public ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(requestListener);
        return servletListenerRegistrationBean;
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public WsSocketHandle getWsHandler(){
        return new WsServer();
    }

}
