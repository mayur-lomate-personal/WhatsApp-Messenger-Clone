package com.mayur.websocket.Controllers;

import com.mayur.websocket.RequestPayloads.UserMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Slf4j
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) {
        log.info(message + "received");
        return message;
    }

    @MessageMapping("/messages")
    public void messages(@Payload UserMessage userMessage, Principal user) {
        log.info("message");
        log.info(userMessage.getTo() + " " + userMessage.getMessage());
        if(userMessage.getTo() != null && !userMessage.getTo().equals("")) {
            simpMessagingTemplate.convertAndSendToUser(userMessage.getTo(), "/queue/messages", user.getName() + " : " +userMessage.getMessage());
        }
    }
}
