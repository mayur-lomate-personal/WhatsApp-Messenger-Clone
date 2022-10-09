package com.mayur.websocket.Filters;

import com.mayur.websocket.Entities.WebSocketUser;
import com.mayur.websocket.Services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Slf4j
@Component
public class CustomHandShakeHandler extends DefaultHandshakeHandler {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private Base32 base32;

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        log.info("here");
        String username = "abc";
        for (String query : request.getURI().getQuery().split("&")) {
            if(query.split("=")[0].equals("jwt") && query.split("=").length > 1 && !query.split("=")[1].equals("")) {
                username = jwtService.getUsernameFromToken(new String(base32.decode(query.split("=")[1])));
            }
        }
        return new WebSocketUser(username);
    }
}
