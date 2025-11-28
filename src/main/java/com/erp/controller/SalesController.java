package com.erp.controller;

import com.erp.service.SalesChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesChartService salesChartService;

    @GetMapping("salesMain")
    private String salesMain(){ return "sales/salesMainUI"; }
}
