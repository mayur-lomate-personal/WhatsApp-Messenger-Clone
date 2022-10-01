package com.mayur.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class WebSocketService {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) {
        log.info(message + "received");
        return message;
    }
}
