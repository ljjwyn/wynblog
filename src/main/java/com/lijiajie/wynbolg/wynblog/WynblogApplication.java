package com.lijiajie.wynbolg.wynblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class WynblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WynblogApplication.class, args);
    }

}
