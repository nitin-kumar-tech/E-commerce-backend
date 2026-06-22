package com.nitin.ecommerce.controller;

import com.nitin.ecommerce.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final GreetingService greetingService;

    public HomeController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/")
    public String home() {
        return "Ecommerce Backend Running";
    }

    @GetMapping("/about")
    public String about() {
        return "This is my first Spring Boot application";
    }

    @GetMapping("/greeting")
    public String greeting() {
        return greetingService.getGreeting();
    }

    @GetMapping("/test-security")
    public String testSecurity() {
        return "Security Test";
    }
}