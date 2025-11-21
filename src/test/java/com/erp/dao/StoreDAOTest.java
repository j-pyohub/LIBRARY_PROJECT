package com.erp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreDAOTest {
    @Autowired
    private StoreDAO storeDAO;

    @Test
    void getStoresTest() {
        System.out.println(storeDAO.getStores());
    }
    @Test
    void getStoresByAddressTest() {
        System.out.println(storeDAO.getStoresByAddress("서울"));
    }
    @Test
    void getStoresByStoreNameTest() {
        System.out.println(storeDAO.getStoresByStoreName("가산"));
    }
    @Test
    void getStoresByManagerNameTest() {
        System.out.println(storeDAO.getStoresByManagerName("수정"));
    }

}
