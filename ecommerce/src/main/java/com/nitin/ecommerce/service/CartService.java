package com.nitin.ecommerce.service;

import com.nitin.ecommerce.dto.AddToCartRequest;
import com.nitin.ecommerce.entity.*;
import com.nitin.ecommerce.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(
            CartRepository cartRepository,
            ProductRepository productRepository,
            UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart addToCart(
            String username,
            AddToCartRequest request) {

        User user =
                userRepository.findByUsername(username);

        Cart cart =
                cartRepository.findByUserId(user.getId());

        if(cart == null){

            cart = new Cart();
            cart.setUser(user);
            cart.setItems(new ArrayList<>());
        }

        Product product =
                productRepository.findById(
                                request.getProductId())
                        .orElseThrow();

        CartItem item = new CartItem();

        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(
                request.getQuantity());

        cart.getItems().add(item);

        return cartRepository.save(cart);
    }

    public Cart getMyCart(
            String username){

        User user =
                userRepository.findByUsername(username);

        return cartRepository.findByUserId(
                user.getId());
    }
}
