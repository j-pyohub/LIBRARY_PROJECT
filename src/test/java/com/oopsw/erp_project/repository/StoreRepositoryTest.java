package com.oopsw.erp_project.repository;

import jakarta.transaction.Transactional;
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
    @Test
    public void getStoreByStoreName(){
        System.out.println(storeRepository.findByStoreNameContaining("가산"));
    }
    @Test
    @Transactional
    public void getStoreByManagerName(){
        System.out.println(storeRepository.findByManager_ManagerNameContaining("수정"));
    }

}
