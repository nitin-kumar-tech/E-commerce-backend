package com.nitin.ecommerce.controller;

import com.nitin.ecommerce.entity.Order;
import com.nitin.ecommerce.entity.OrderStatus;
import com.nitin.ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;
import com.nitin.ecommerce.dto.OrderRequest;
import org.springframework.data.domain.Page;
import java.security.Principal;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService) {

        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(
            @RequestBody Order order) {

        return orderService.save(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();
    }

    @PostMapping("/create")
    public Order createRealOrder(
            @RequestBody OrderRequest request,
            Principal principal) {

        return orderService.createOrder(
                request,
                principal.getName()
        );
    }

    @GetMapping("/{id}")
    public Order getOrderById(
            @PathVariable Integer id) {

        return orderService.getOrderById(id);
    }

    @GetMapping("/expensive")
    public List<Order> getOrdersAboveAmount(
            @RequestParam Double amount) {

        return orderService
                .getOrdersAboveAmount(amount);
    }

    @GetMapping("/page")
    public Page<Order> getOrdersPage(
            @RequestParam int page,
            @RequestParam int size) {

        return orderService.getOrders(
                page,
                size
        );
    }

    @GetMapping("/sorted")
    public List<Order> getOrdersSorted() {

        return orderService.getOrdersSortedByAmount();
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(
            @PathVariable Integer id,
            @RequestParam OrderStatus status) {

        return orderService.updateStatus(
                id,
                status
        );
    }

    @PutMapping("/{id}/cancel")
    public Order cancelOrder(
            @PathVariable Integer id) {

        return orderService.cancelOrder(id);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(
            @PathVariable Integer userId) {

        return orderService.getOrdersByUser(userId);
    }

    @GetMapping("/my-orders")
    public List<Order> getMyOrders(
            Principal principal) {

        return orderService.getMyOrders(
                principal.getName()
        );
    }
}
