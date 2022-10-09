package com.mayur.clients.database.ResponsePayloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
}
