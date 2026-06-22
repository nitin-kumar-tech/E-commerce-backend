package com.nitin.ecommerce.repository;

import com.nitin.ecommerce.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository
        extends JpaRepository<Wishlist,Integer> {

    Wishlist findByUserId(Integer userId);
}
