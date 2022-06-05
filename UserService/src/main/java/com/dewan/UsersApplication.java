package com.dewan;

import com.dewan.controller.UsersAppController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class UsersApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }
}