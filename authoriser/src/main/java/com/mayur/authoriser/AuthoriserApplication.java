package com.mayur.authoriser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableFeignClients(
        basePackages = "com.mayur.clients"
)
@SpringBootApplication
@EnableWebSecurity
@EnableEurekaClient
public class AuthoriserApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthoriserApplication.class, args);
    }
}
