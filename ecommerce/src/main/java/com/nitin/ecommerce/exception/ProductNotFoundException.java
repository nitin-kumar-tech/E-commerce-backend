package com.nitin.ecommerce.exception;

public class ProductNotFoundException
        extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
