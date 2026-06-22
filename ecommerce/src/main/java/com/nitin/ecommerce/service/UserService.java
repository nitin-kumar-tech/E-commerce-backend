package com.nitin.ecommerce.service;

import com.nitin.ecommerce.dto.RegisterRequest;
import com.nitin.ecommerce.entity.User;
import com.nitin.ecommerce.repository.UserRepository;
import com.nitin.ecommerce.dto.LoginRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User register(RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole("USER");

        return userRepository.save(user);
    }
    public String login(LoginRequest request) {

        User user = userRepository
                .findByUsername(request.getUsername());

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!matches) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(
                user.getUsername()
        );
    }

    public User registerAdmin(RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole("ADMIN");

        return userRepository.save(user);
    }
}