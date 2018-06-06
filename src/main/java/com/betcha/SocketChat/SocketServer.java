package com.betcha.SocketChat;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/actions")

public class SocketServer {


    @OnOpen
    public void open(Session session) {
    }

    @OnClose
    public void close(Session session) {
    }

    @OnError
    public void onError(Throwable error) {
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
    }
}
