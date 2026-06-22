package com.nitin.ecommerce.controller;

import com.nitin.ecommerce.dto.DashboardResponse;
import com.nitin.ecommerce.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardResponse getDashboard() {

        return dashboardService.getDashboard();
    }
}
