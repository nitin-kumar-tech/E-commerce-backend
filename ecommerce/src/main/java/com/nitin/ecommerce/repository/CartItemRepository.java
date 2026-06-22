package com.nitin.ecommerce.repository;

import com.nitin.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository
        extends JpaRepository<CartItem,Integer> {
}
