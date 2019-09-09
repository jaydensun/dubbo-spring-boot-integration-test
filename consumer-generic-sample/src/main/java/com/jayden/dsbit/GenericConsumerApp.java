package com.jayden.dsbit;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GenericConsumerApp {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(version = "1.0.0", generic = true, interfaceClass = DemoService.class)
    private GenericService demoService;

    public static void main(String[] args) {
        SpringApplication.run(GenericConsumerApp.class).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            logger.info(String.valueOf(demoService.$invoke("sayHello", new String[]{String.class.getName()},
                    new String[] {"mercyblitz"})));
        };
    }

    @Bean
    public ApplicationRunner runner2() {
        return args -> {
            logger.info(String.valueOf(demoService.$invoke("sayHello", new String[]{String.class.getName()},
                    new String[] {"mercyblitz2"})));
        };
    }
}