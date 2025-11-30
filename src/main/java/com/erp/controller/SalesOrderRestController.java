package com.erp.controller;

import com.erp.dto.SalesOrderDTO;
import com.erp.service.SalesOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalesOrderRestController {
    private final SalesOrderService salesOrderService;
    @GetMapping("salesOrderList")
    public List<SalesOrderDTO> getAllSalesOrder(){
        return salesOrderService.getSalesOrderList();
    }
}
