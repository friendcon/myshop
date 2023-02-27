package com.project.myshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableJpaAuditing
@EnableRedisHttpSession
@SpringBootApplication
public class MyshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyshopApplication.class, args);
    }

}
