package com.nitin.ecommerce.service;

import com.nitin.ecommerce.entity.*;
import com.nitin.ecommerce.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WishlistService(
            WishlistRepository wishlistRepository,
            UserRepository userRepository,
            ProductRepository productRepository) {

        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Wishlist addToWishlist(
            String username,
            Integer productId) {

        User user =
                userRepository.findByUsername(username);

        Wishlist wishlist =
                wishlistRepository.findByUserId(
                        user.getId());

        if(wishlist == null){

            wishlist = new Wishlist();
            wishlist.setUser(user);
            wishlist.setProducts(
                    new ArrayList<>());
        }

        Product product =
                productRepository.findById(productId)
                        .orElseThrow();

        wishlist.getProducts().add(product);

        return wishlistRepository.save(wishlist);
    }

    public Wishlist getMyWishlist(
            String username){

        User user =
                userRepository.findByUsername(username);

        return wishlistRepository.findByUserId(
                user.getId());
    }
}
