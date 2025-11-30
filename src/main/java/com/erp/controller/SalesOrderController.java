package com.erp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SalesOrderController {
    @GetMapping("salesOrder")
    public String salesOrder() {
        return "sales/salesOrderManagerUI";
    }
}
