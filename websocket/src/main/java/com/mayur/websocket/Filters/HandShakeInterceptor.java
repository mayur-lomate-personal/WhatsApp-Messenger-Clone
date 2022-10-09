package com.mayur.websocket.Filters;

import com.mayur.websocket.Services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
@Slf4j
public class HandShakeInterceptor implements HandshakeInterceptor {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private Base32 base32;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info(request.getURI().toASCIIString());
        for (String query : request.getURI().getQuery().split("&")) {
            if(query.split("=")[0].equals("jwt") && query.split("=").length > 1 && !query.split("=")[1].equals("")) {
                log.info(base32.toString());
                log.info(new String(base32.decode(query.split("=")[1])));
                try {
                    if(!jwtService.isTokenExpired(new String(base32.decode(query.split("=")[1])))) {
                        log.info(jwtService.getUsernameFromToken(new String(base32.decode(query.split("=")[1]))));
                        return true;
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
