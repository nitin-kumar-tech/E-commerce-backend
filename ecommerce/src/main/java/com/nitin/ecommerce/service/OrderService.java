package com.nitin.ecommerce.service;

import com.nitin.ecommerce.dto.OrderItemRequest;
import com.nitin.ecommerce.dto.OrderRequest;
import com.nitin.ecommerce.entity.Order;
import com.nitin.ecommerce.entity.OrderItem;
import com.nitin.ecommerce.entity.OrderStatus;
import com.nitin.ecommerce.entity.Product;
import com.nitin.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;
import com.nitin.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.nitin.ecommerce.repository.UserRepository;
import com.nitin.ecommerce.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderService(
            OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {

        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Order save(Order order) {

        if (order.getOrderItems() != null) {

            for (OrderItem item : order.getOrderItems()) {

                item.setOrder(order);
            }
        }

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    public Order createOrder(
            OrderRequest request,
            String username) {

        User user =
                userRepository.findByUsername(username);

        if (user == null) {

            throw new RuntimeException(
                    "User not found"
            );
        }

        Order order = new Order();

        double totalAmount = 0;

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(
                            itemRequest.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException("Product not found"));
            if(itemRequest.getQuantity() > product.getStock()) {

                throw new RuntimeException(
                        "Not enough stock for product : "
                                + product.getName()
                );
            }

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            product.setStock(
                    product.getStock()
                            - itemRequest.getQuantity()
            );

            productRepository.save(product);

            totalAmount +=
                    product.getPrice()
                            * itemRequest.getQuantity();

            orderItems.add(orderItem);
        }

        order.setTotalAmount(totalAmount);
        order.setOrderItems(orderItems);
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);

        return orderRepository.save(order);
    }

    public Order getOrderById(Integer id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersAboveAmount(
            Double amount) {

        return orderRepository
                .findByTotalAmountGreaterThan(amount);
    }

    public Page<Order> getOrders(
            int page,
            int size) {

        return orderRepository.findAll(
                PageRequest.of(page, size)
        );
    }

    public List<Order> getOrdersSortedByAmount() {

        return orderRepository.findAll(
                Sort.by("totalAmount")
        );
    }

    public Order updateStatus(
            Integer id,
            OrderStatus status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.PENDING);

        return orderRepository.save(order);
    }

    public Order cancelOrder(Integer id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.CANCELLED);

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(
            Integer userId) {

        return orderRepository.findByUserId(userId);
    }

    public List<Order> getMyOrders(
            String username) {

        User user =
                userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException(
                    "User not found"
            );
        }

        return orderRepository.findByUserId(
                user.getId()
        );
    }
}
