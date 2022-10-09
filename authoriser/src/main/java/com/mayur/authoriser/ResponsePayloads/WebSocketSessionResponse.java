package com.mayur.authoriser.ResponsePayloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WebSocketSessionResponse {
    private String message;
    private int status;
    private String jwtToken;
}
