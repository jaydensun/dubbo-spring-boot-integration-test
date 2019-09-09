package com.jayden.dsbit;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

@Service(version = "1.0.0", dynamic = true)
public class DemoServiceImpl implements DemoService {

    /**
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     */
    @Value("${dubbo.application.name}")
    private String serviceName;

    public HelloResponse sayHello(String name) {
        return new HelloResponse(String.format("[%s] : Hello, %s", serviceName, name));
    }
}