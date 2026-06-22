package com.nitin.ecommerce.controller;

import com.nitin.ecommerce.dto.RegisterRequest;
import com.nitin.ecommerce.entity.User;
import com.nitin.ecommerce.service.UserService;
import com.nitin.ecommerce.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(
            UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(
            @RequestBody RegisterRequest request) {

        return userService.register(request);
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        return userService.login(request);
    }

//    @PostMapping("/register-admin")
//    public User registerAdmin(
//            @RequestBody RegisterRequest request) {
//
//        return userService.registerAdmin(request);
//    }
}