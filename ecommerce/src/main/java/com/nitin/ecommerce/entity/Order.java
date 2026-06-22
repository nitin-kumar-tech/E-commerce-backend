package com.nitin.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL
    )
    private List<OrderItem> orderItems;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
