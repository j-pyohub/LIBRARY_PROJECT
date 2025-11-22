package com.erp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
public class SalesOrderRepositoryTest {
    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Test
    public void countOrdersTest() {
        System.out.println(salesOrderRepository.countOrders(1L, Date.valueOf("2025-12-30")));
    }
}
