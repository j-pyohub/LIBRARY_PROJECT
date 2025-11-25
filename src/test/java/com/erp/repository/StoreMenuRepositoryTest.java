package com.erp.repository;


import com.erp.repository.entity.Menu;
import com.erp.repository.entity.Store;
import com.erp.repository.entity.StoreMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreMenuRepositoryTest {
    @Autowired
    private StoreMenuRepository storeMenuRepository;


    @Test
    void setStoreMenuTest(){
        Long storeMenuNo = 48L;
        String salesStatus = "판매중";
        StoreMenu sm = storeMenuRepository.findById(storeMenuNo).get();
        sm.setSalesStatus(salesStatus);

        storeMenuRepository.save(sm);
    }

    @Test
    void addStoreMenuTest(){

        Store store = Store.builder()
                .storeNo(1L)
                .build();

        Menu menu = Menu.builder()
                .menuNo(10L)
                .build();

        StoreMenu sm = StoreMenu.builder().store(store).menu(menu).salesStatus("판매중단").
                build();
        System.out.println(storeMenuRepository.save(sm));
    }

    @Test
    void findStoreMenuForStoreTest(){
        System.out.println(storeMenuRepository.findStoreMenuForStore(1L,null,null,null));
    }

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
