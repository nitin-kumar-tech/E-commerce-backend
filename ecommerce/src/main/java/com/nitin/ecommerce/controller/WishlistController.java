package com.nitin.ecommerce.controller;

import com.nitin.ecommerce.entity.Wishlist;
import com.nitin.ecommerce.service.WishlistService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(
            WishlistService wishlistService) {

        this.wishlistService = wishlistService;
    }

    @PostMapping("/{productId}")
    public Wishlist addToWishlist(
            Principal principal,
            @PathVariable Integer productId) {

        return wishlistService.addToWishlist(
                principal.getName(),
                productId);
    }

    @GetMapping
    public Wishlist getMyWishlist(
            Principal principal) {

        return wishlistService.getMyWishlist(
                principal.getName());
    }
}
