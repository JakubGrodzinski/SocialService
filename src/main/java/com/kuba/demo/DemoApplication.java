package com.kuba.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.SpringTemplateEngine;

@SpringBootApplication
public class DemoApplication
{
    
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
