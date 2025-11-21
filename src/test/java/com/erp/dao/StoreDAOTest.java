package com.erp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreDAOTest {
    @Autowired
    private StoreDAO storeDAO;

    @Test
    void getStores() {
        System.out.println(storeDAO.getStores());
    }
    @Test
    void getStoresByAddress() {
        System.out.println(storeDAO.getStoresByAddress("서울"));

    }

}
