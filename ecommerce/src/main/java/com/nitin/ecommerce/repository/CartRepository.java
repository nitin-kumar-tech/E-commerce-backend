package com.nitin.ecommerce.repository;

import com.nitin.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository
        extends JpaRepository<Cart,Integer> {

    Cart findByUserId(Integer userId);
}
