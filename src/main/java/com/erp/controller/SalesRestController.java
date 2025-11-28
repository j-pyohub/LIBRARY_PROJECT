package com.erp.controller;

import com.erp.dto.SalesChartDTO;
import com.erp.service.SalesChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SalesRestController {

    private final SalesChartService salesChartService;

    @GetMapping("salesChart")
    public SalesChartDTO getSalesChart(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String type

    ) {
        return salesChartService.getSalesChartByDate(startDate,endDate,type);
    }
}
