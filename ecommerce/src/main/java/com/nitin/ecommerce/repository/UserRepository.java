package com.nitin.ecommerce.repository;

import com.nitin.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
