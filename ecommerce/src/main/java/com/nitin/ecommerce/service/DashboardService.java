package com.nitin.ecommerce.service;

import com.nitin.ecommerce.dto.DashboardResponse;
import com.nitin.ecommerce.entity.OrderStatus;
import com.nitin.ecommerce.repository.*;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;

    public DashboardService(
            UserRepository userRepository,
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            OrderRepository orderRepository) {

        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
    }

    public DashboardResponse getDashboard() {

        return new DashboardResponse(

                userRepository.count(),
                productRepository.count(),
                categoryRepository.count(),
                orderRepository.count(),

                orderRepository.countByStatus(
                        OrderStatus.PENDING),

                orderRepository.countByStatus(
                        OrderStatus.CONFIRMED),

                orderRepository.countByStatus(
                        OrderStatus.SHIPPED),

                orderRepository.countByStatus(
                        OrderStatus.DELIVERED),

                orderRepository.countByStatus(
                        OrderStatus.CANCELLED)
        );
    }
}
