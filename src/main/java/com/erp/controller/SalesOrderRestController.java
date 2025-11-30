package com.erp.controller;

import com.erp.dto.ItemOrderDTO;
import com.erp.dto.SalesOrderDTO;
import com.erp.service.SalesOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SalesOrderRestController {
    private final SalesOrderService salesOrderService;

    @GetMapping("/salesOrder/salesOrderList/{pageNo}")
    public Map<String, Object> getSalesOrderList(@PathVariable int pageNo) {
        Page<SalesOrderDTO> page = salesOrderService.getSalesOrderList(pageNo);

        return Map.of(
                "list", page.getContent(),
                "totalPages", page.getTotalPages(),
                "pageNo", page.getNumber() + 1,
                "totalElements", page.getTotalElements()
        );
    }

    }
