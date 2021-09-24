package com.example.armeriademo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.linecorp.armeria.spring.ArmeriaServerConfigurator;

@Configuration(proxyBeanMethods = false)
public class DemoAppConfiguration {

    @Bean
    HelloService helloService() {
        return new HelloService();
    }
    @Bean
    ArmeriaServerConfigurator demoArmeriaServerConfigurator(HelloService helloService) {
        return serverBuilder -> {
            serverBuilder.annotatedService(helloService);
        };
    }
}
