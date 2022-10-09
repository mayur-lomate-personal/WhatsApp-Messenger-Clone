package com.mayur.clients.database;

import com.mayur.clients.database.RequestPayloads.UserRegistrationRequest;
import com.mayur.clients.database.ResponsePayloads.UserRegisterResponse;
import com.mayur.clients.database.ResponsePayloads.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(
        value = "localhost:8091"
)
public interface DatabaseClient {
    @PostMapping(path = "/api/user/save", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserRegisterResponse registerUser(@Valid @RequestBody UserRegistrationRequest user);

    @GetMapping(path = "/api/user/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse getUser(@PathVariable("username") String username);
}
