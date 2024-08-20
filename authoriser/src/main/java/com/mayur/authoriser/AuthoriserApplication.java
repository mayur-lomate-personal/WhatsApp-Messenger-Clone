package com.mayur.authoriser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableFeignClients(
        basePackages = "com.mayur.clients"
)
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
@SpringBootApplication
@EnableWebSecurity
@EnableEurekaClient
public class AuthoriserApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthoriserApplication.class, args);
    }
}
