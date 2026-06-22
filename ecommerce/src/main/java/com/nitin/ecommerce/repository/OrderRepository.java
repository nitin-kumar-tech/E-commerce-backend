package com.nitin.ecommerce.repository;

import com.nitin.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nitin.ecommerce.entity.OrderStatus;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Integer> {

    List<Order> findByTotalAmountGreaterThan(
            Double amount);

    List<Order> findByUserId(Integer userId);

    long countByStatus(OrderStatus status);
}
