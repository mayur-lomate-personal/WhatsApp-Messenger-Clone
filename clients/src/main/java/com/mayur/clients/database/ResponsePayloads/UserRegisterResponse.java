package com.mayur.clients.database.ResponsePayloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class UserRegisterResponse {
    private String message;
    private int status;
}
