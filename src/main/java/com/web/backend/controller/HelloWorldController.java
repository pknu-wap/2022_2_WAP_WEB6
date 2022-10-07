package com.web.backend.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/api/helloworld")
    public String test() {
        return "Hello World!";
    }
}