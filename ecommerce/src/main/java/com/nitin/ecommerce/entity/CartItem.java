package com.nitin.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;

    private Integer quantity;
}
