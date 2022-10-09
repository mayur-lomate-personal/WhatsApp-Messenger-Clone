package com.mayur.database.Controllers;

import com.mayur.clients.database.RequestPayloads.UserRegistrationRequest;
import com.mayur.clients.database.ResponsePayloads.UserRegisterResponse;
import com.mayur.clients.database.ResponsePayloads.UserResponse;
import com.mayur.database.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/api/user/save", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserRegisterResponse registerUser(@Valid @RequestBody UserRegistrationRequest user) {
        return userService.saveUser(user);
    }

    @GetMapping(path = "/api/user/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }
}
