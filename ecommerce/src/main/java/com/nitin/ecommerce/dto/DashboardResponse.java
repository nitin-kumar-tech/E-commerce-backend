package com.nitin.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardResponse {

    private long totalUsers;
    private long totalProducts;
    private long totalCategories;
    private long totalOrders;

    private long pendingOrders;
    private long confirmedOrders;
    private long shippedOrders;
    private long deliveredOrders;
    private long cancelledOrders;
}
