package com.nitin.ecommerce.dto;

import lombok.Data;

@Data
public class AddToCartRequest {

    private Integer productId;

    private Integer quantity;
}
