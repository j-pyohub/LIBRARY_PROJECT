package com.erp.repository;

import com.erp.repository.entity.ItemOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemOrderDetailRepositoryTest {

    @Autowired
    private ItemOrderDetailRepository repo;

    @Test
    void getAlItemOrderDetail(){
        repo.findByItemOrderNo(ItemOrder.builder().itemOrderNo(1L).build()).forEach(System.out::println);
    }
}
