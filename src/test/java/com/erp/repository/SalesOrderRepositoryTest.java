package com.erp.repository;

import com.erp.repository.entity.SalesOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class SalesOrderRepositoryTest {
    @Autowired
    private SalesOrderRepository salesOrderRepository;
    @Autowired
    private StoreRepository storeRepository;

    @Test
    @Transactional
    public void getSalesOrderTest() {
        var list = salesOrderRepository.findAll();
        for (SalesOrder s : list) {
            System.out.println(s.getSalesOrderNo());
            System.out.println(s.getStore().getStoreNo());
            System.out.println(s.getSalesOrderDatetime());
            System.out.println(s.getSalesOrderAmount());
        }
    }
}
