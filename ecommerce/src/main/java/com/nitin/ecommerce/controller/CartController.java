package com.nitin.ecommerce.controller;

import com.nitin.ecommerce.dto.AddToCartRequest;
import com.nitin.ecommerce.entity.Cart;
import com.nitin.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(
            CartService cartService) {

        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart addToCart(
            Principal principal,
            @RequestBody AddToCartRequest request) {

        return cartService.addToCart(
                principal.getName(),
                request
        );
    }

    @GetMapping
    public Cart getMyCart(
            Principal principal) {

        return cartService.getMyCart(
                principal.getName()
        );
    }
}
