package com.mayur.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories
@ComponentScan
@EnableEurekaClient
public class DatabaseOperationApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatabaseOperationApplication.class, args);
    }
}
