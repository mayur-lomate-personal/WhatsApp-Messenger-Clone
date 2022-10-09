package com.mayur.websocket.Filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.security.Principal;
import java.util.HashMap;

@Slf4j
public class InputChannelInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.DISCONNECT == accessor.getCommand()) {
            log.info("my disconnect");
        } else if (StompCommand.CONNECT == accessor.getCommand()) {
            log.info("my connect");
        }
        Principal user = accessor.getUser();
        //log.info(message.getPayload().getClass().getName());
        log.info("sessAttr " + user.getName());
        return message;
    }
}
