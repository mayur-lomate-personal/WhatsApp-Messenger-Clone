package com.mayur.websocket.Entities;

import java.security.Principal;

public class WebSocketUser implements Principal {

    private final String username;

    public WebSocketUser(String username) {
        this.username = username;
    }

    @Override
    public String getName() {
        return username;
    }
}
