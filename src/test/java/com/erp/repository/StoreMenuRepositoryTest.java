package com.erp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreMenuRepositoryTest {
    @Autowired
    private StoreMenuRepository storeMenuRepository;



    @Test
    void findSellingMenuByMenuNameTest(){
        System.out.println(storeMenuRepository.findStoreMenu(null,"치즈",null,null));
    }


    @Test
    void findSellingMenuByStoreNameAndSalesStatusTest() {
        System.out.println(storeMenuRepository.findStoreMenu("가산",null,"품절",null));
    }

    @Test
    void findSellingMenuByStoreNameAndMenuCategoryTest() {
        System.out.println(storeMenuRepository.findStoreMenu("가산",null,null,"피자"));
    }

    @Test
    void findSellingMenuByStoreNameTest(){
        System.out.println(storeMenuRepository.findStoreMenu("가산",null,null,null));
    }

    @Test
    public void setSalesStatusTest() {
        int result = storeMenuRepository.setSalesStatus(40L, "판매중");
        if (result == 0) {
            throw new IllegalArgumentException("해당 store_menu_no 없음: " + 40L);
        }
    }


}
