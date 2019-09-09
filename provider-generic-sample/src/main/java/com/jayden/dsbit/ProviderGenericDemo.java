package com.jayden.dsbit;

import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
public class ProviderGenericDemo {

    public static void main(String[] args) {
        SpringApplication.run(ProviderGenericDemo.class,args);
    }

    @Bean
    public static void service() {
        ServiceConfig<GenericService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface("com.jayden.dsbit.DemoService");
        serviceConfig.setDynamic(true);
        serviceConfig.setVersion("1.0.0");
        serviceConfig.setRef((method, parameterTypes, args) -> "====== hello : " + args[0]);
        serviceConfig.export();
    }
}