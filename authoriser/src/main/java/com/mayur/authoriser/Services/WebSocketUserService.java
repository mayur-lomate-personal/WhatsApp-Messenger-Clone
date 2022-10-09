package com.mayur.authoriser.Services;

import com.mayur.authoriser.ResponsePayloads.JwtResponse;
import com.mayur.authoriser.ResponsePayloads.WebSocketSessionResponse;
import com.mayur.clients.database.DatabaseClient;
import com.mayur.clients.database.RequestPayloads.UserRegistrationRequest;
import com.mayur.clients.database.ResponsePayloads.UserRegisterResponse;
import feign.Client;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Slf4j
@Service
public class WebSocketUserService {

    @Autowired
    private DatabaseClient databaseClient;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Base32 base32;

    public UserRegisterResponse registerUser(UserRegistrationRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return databaseClient.registerUser(user);
    }

    public JwtResponse getJwtToken(String username, String password) {
        log.info("username " + username + "password " + password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            log.error("Invalid Credentials");
        }
        log.info("creating");
        return new JwtResponse("Token Generated", HttpStatus.OK.value(), jwtService.generateTokenUsingUsername(username, 5*60*60));
    }

    public WebSocketSessionResponse getWebSocketSession(String username) {
        return new WebSocketSessionResponse("Session Generated", HttpStatus.OK.value(), base32.encodeAsString(jwtService.generateTokenUsingUsername(username, 30).getBytes()));
    }
}
