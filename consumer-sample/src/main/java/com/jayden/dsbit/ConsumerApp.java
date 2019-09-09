package com.jayden.dsbit;

import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumerApp {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(version = "1.0.0")
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            logger.info(demoService.sayHello("mercyblitz").toString());
        };
    }

    @Bean
    public ApplicationRunner runner2() {
        return args -> {
            logger.info(demoService.sayHello("mercyblitz2").toString());
        };
    }

}