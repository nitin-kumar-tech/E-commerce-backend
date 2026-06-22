package com.nitin.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private List<OrderItemRequest> items;
}
