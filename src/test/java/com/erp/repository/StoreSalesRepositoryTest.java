package com.erp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.time.LocalDate;

@SpringBootTest
@Transactional
public class StoreSalesRepositoryTest {
    @Autowired
    private StoreSalesRepository storeSalesRepository;

    @Test
    public void findBySalesDateBetweenTest() {
        System.out.println(storeSalesRepository.findBySalesDateBetween(LocalDate.of(2024,1,9), LocalDate.of(2024,1,14)));
    }

    @Test
    public void findByStore_StoreNoAndSalesDateBetweenTest() {
        System.out.println(storeSalesRepository.findByStore_StoreNoAndSalesDateBetween(1L, LocalDate.of(2025,12,27), LocalDate.of(2025,12,30)));
    }
}
