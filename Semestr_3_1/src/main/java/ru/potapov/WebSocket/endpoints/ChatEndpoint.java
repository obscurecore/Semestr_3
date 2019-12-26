package ru.potapov.WebSocket.endpoints;



import ru.potapov.WebSocket.coders.MessageDecoder;
import ru.potapov.WebSocket.coders.MessageEncoder;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@ServerEndpoint(value = "/chat" , decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
public class ChatEndpoint {
    private Session session = null;
    private static List<Session> sessionList =  new LinkedList<>();
    @OnOpen
    public void onOpen(Session session){
        this.session=session; //remember, that this endpoint
        sessionList.add(session);
    }
    @OnClose
    public void onClose(Session session){
        sessionList.remove(session);
    }
    @OnError
    public void onError(Session session, Throwable throwable){
        throwable.printStackTrace();
    }
    @OnMessage
    public void onMessage(Session session, String msg){

        sessionList.forEach(s->{
            if(s== this.session) return;
            try {
                s.getBasicRemote().sendObject(msg);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        });
    }
}
