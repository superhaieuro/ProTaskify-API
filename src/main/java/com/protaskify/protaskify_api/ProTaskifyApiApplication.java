package com.protaskify.protaskify_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ProTaskifyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProTaskifyApiApplication.class, args);
    }

}
