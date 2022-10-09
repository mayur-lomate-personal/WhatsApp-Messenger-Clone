package com.mayur.authoriser.ResponsePayloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse {
    private String message;
    private int status;
    private String jwtToken;
}
