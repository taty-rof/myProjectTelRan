package com.telran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = "com.telran")
@SpringBootApplication
public class HiMathApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiMathApplication.class, args);
    }

}
