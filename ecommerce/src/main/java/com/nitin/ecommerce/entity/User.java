package com.nitin.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String role;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
