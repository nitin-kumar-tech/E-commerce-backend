package com.nitin.ecommerce.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getGreeting() {
        return "Welcome to my Ecommerce Project";
    }
}