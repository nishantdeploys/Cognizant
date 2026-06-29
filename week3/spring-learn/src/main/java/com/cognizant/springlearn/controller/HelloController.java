package com.cognizant.springlearn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple REST Controller to verify the setup of the Spring Boot application.
 */
@RestController
public class HelloController {

    /**
     * Endpoint to check text response.
     * Accessible at: http://localhost:8082/hello
     */
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Welcome to the Spring Boot Learning Program!";
    }

    /**
     * Endpoint to check JSON response and request parameter binding.
     * Accessible at: http://localhost:8082/info?name=YourName
     */
    @GetMapping("/info")
    public Map<String, Object> getInfo(@RequestParam(value = "name", defaultValue = "Learner") String name) {
        Map<String, Object> info = new HashMap<>();
        info.put("message", "Spring Boot is awesome!");
        info.put("learnerName", name);
        info.put("status", "Active");
        info.put("week", 3);
        return info;
    }
}
