package com.example.armeriademo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.armeriademo.ArmeriaDemoApplicationTests.Config;

import com.linecorp.armeria.client.WebClient;
import com.linecorp.armeria.common.AggregatedHttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.spring.LocalArmeriaPort;

@SpringBootTest(classes = { DemoAppConfiguration.class, Config.class })
@EnableAutoConfiguration
class ArmeriaDemoApplicationTests {

    @Autowired
    WebClient webClient;

    @Test
    void hello() {
        AggregatedHttpResponse response = webClient.get("/hello")
                                                   .aggregate().join();
        assertThat(response.status())
                .isEqualTo(HttpStatus.OK);
    }

    @Configuration
    static class Config {
        @LocalArmeriaPort
        Integer localPort;

        @Bean
        WebClient testClient() {
            return WebClient.of("http://127.0.0.1:" + localPort);
        }

    }

}
