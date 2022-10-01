package com.mayur.websocket_test_java;


import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class WebSocketTestMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        WebSocketClient simpleWebSocketClient =
                new StandardWebSocketClient();
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(simpleWebSocketClient));
        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        //String url = "ws://localhost:9090/chat";
        String url = "ws://localhost:8090/websocket";
        StompSessionHandler stompSessionHandler = new WebScoketMessageHandler();
        StompSession session = stompClient.connect(url, stompSessionHandler).get(1, TimeUnit.SECONDS);
        session.subscribe("/topic/greetings", stompSessionHandler);
        session.send("/app/hello", "mayur");
        session.disconnect();

    }
}
