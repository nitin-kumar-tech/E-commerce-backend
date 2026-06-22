package com.nitin.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Data
@Entity
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
