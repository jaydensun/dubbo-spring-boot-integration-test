package com.jayden.dsbit;

import org.apache.dubbo.config.spring.ServiceBean;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

@EnableAutoConfiguration
public class ProviderGenericDemo {

    public static void main(String[] args) {
        SpringApplication.run(ProviderGenericDemo.class, args);
    }

    @Bean
    public static ServiceBean<GenericService> service() {
        ServiceBean<GenericService> serviceBean = new ServiceBean<>();
        serviceBean.setInterface("com.jayden.dsbit.DemoService");
        serviceBean.setDynamic(true);
        serviceBean.setVersion("1.0.0");
        serviceBean.setRef((method, parameterTypes, args) -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("msg", "hello : " + args[0]);
            return map;
        });
        return serviceBean;
    }
}