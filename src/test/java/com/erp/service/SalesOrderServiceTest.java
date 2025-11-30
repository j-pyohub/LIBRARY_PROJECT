package com.erp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class SalesOrderServiceTest {
    @Autowired
    SalesOrderService salesOrderService;

    @Test
    public void getSalesOrderTest() {
        System.out.println(salesOrderService.getSalesOrderList(1, null,null));
    }

    @Test
    public void getSalesOrderDetailTest() {
        System.out.println(salesOrderService.getSalesOrderDetail(655L));
    }

}
