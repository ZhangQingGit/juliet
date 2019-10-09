package com.ingooo.juliet.config;

import com.ingooo.websocket.util.handler.WsSocketHandle;

import javax.websocket.Session;

/**
 * Create by 丶TheEnd on 2019/10/9 0009.
 * @author Administrator
 */
public class WsServer implements WsSocketHandle {


    @Override
    public void onOpen(Session session) {
        System.out.println("onOpen");
    }

    @Override
    public void onClose(Session session) {
        System.out.println("onClose");
    }

    @Override
    public void onMessage(String message, Session session) {
        System.out.println("onMessage");
    }

    @Override
    public void onError(Session session, Throwable error) {
        System.out.println("onError");
    }
}
