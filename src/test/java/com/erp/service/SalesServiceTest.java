package com.erp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class SalesServiceTest {
    @Autowired
    private SalesService salesService;

    @Test
    void getTotalStoreSalesTest(){
        System.out.println(salesService.getTotalStoreSales());
    }

    @Test
    void getSalesChartTest(){

        LocalDate start = LocalDate.of(2025, 11, 1);
        LocalDate end   = LocalDate.of(2025, 11, 25);
        String type     = "day";

        System.out.println(salesService.getSalesChart(start,end, type));

    }
}
