package com.nitin.ecommerce.repository;

import com.nitin.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository
    extends JpaRepository<Product, Integer>{

    List<Product> findByName(String name);

    List<Product> findByPriceLessThan(double price);

    List<Product> findByPriceGreaterThan(double price);

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findExpensiveProducts(
            @Param("price") double price);

    @Query(
            value = "SELECT * FROM product WHERE price > ?1",
            nativeQuery = true
    )
    List<Product> expensiveProductsNative(
            double price);
}

