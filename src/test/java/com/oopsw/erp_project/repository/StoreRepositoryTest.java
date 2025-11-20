package com.oopsw.erp_project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreRepositoryTest {
    @Autowired
    StoreRepository storeRepository;

    @Test
    public void getStore(){
        System.out.println(storeRepository.findAll());
    }

    @Test
    public void getStoreByAddress(){
        System.out.println(storeRepository.findByAddressContaining("송파구"));
    }
}
