package com.mayur.websocket_test_java;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

@Slf4j
public class WebScoketMessageHandler extends StompSessionHandlerAdapter {
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info((String) payload);
    }
}
