package com.nitin.ecommerce.security;

import com.nitin.ecommerce.entity.User;
import com.nitin.ecommerce.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username)
            throws UsernameNotFoundException {

        User user =
                userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(
                    "User not found"
            );
        }

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
