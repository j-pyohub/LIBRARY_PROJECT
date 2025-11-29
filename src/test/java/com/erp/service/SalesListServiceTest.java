package com.erp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class SalesListServiceTest {

    @Autowired
    private SalesListService salesListService;

    @Test
    void getSalesListTest(){
        Pageable pageable = PageRequest.of(0, 10);
        System.out.println(salesListService.getSalesList(null,null,null,pageable).getContent());
    }

}
