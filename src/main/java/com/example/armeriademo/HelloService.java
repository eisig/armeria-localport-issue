package com.example.armeriademo;

import com.linecorp.armeria.server.annotation.Get;

public class HelloService {
    @Get("/hello")
    public String hello() {
        return "hello";
    }
}
