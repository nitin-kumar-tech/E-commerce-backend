package com.nitin.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {

    private Integer id;
    private String name;
    private double price;
}
