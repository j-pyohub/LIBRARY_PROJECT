package com.erp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;


import java.sql.Date;

@SpringBootTest
public class StoreSalesRepositoryTest {
    @Autowired
    private StoreSalesRepository storeSalesRepository;

    @Test
    public void findBySalesDateBetweenTest() {
        System.out.println(storeSalesRepository.findBySalesDateBetween(Date.valueOf("2024-01-05"), Date.valueOf("2024-01-07")));
    }
}
