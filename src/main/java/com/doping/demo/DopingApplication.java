package com.doping.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DopingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DopingApplication.class, args);
    }

}
