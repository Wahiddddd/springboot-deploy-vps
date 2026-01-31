package com.dibimbing.deployment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TestController {

    @Value("${test.fullname}")
    private String fullname;

    @Value("${test.email}")
    private String email;

    @GetMapping("/hello")
    public ResponseEntity<?> helloWorld(@RequestParam String name) {
        return ResponseEntity.ok("Hello " + name + ", welcome to the deployment class!\nI'm " + fullname + ", you can reach me at " + email + ".");
    }
}
