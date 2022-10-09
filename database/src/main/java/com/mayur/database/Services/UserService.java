package com.mayur.database.Services;

import com.mayur.clients.database.RequestPayloads.UserRegistrationRequest;
import com.mayur.clients.database.ResponsePayloads.User;
import com.mayur.clients.database.ResponsePayloads.UserRegisterResponse;
import com.mayur.clients.database.ResponsePayloads.UserResponse;
import com.mayur.database.Entities.WebSocketUser;
import com.mayur.database.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserRegisterResponse saveUser(UserRegistrationRequest userRegistrationRequest) {
        if(userRepo.existsById(userRegistrationRequest.getUsername())) {
            return new UserRegisterResponse("User already Exist", HttpStatus.BAD_REQUEST.value());
        }
        userRepo.save(new WebSocketUser(userRegistrationRequest.getUsername(), userRegistrationRequest.getFirstName(), userRegistrationRequest.getLastName(), userRegistrationRequest.getPassword()));
        return new UserRegisterResponse("User Registered", HttpStatus.OK.value());
    }

    public UserResponse getUser(String username) {
        if(!userRepo.existsById(username)) {
            return new UserResponse("User not Present", HttpStatus.BAD_REQUEST.value(), null);
        }
        WebSocketUser user = userRepo.getById(username);
        return new UserResponse("User Present", HttpStatus.OK.value(), new User(user.getUsername(), user.getFirstName(), user.getLastName(), user.getPassword()));
    }
}
