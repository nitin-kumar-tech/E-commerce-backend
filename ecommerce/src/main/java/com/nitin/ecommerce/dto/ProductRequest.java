package com.nitin.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Positive(message = "Price must be greater than 0")
    private double price;

    @NotNull(message = "Category Id is required")
    private Integer categoryId;

    @Positive(message = "Stock must be greater than 0")
    private Integer stock;
}
