package com.erp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemOrderDetailRepositoryTest {

    @Autowired
    private ItemOrderDetailRepository repo;

    @Test
    void getAllItemOrderDetails() {
        repo.findAll().forEach(System.out::println);
    }
}
