package com.erp.repository;


import com.erp.repository.entity.Menu;
import com.erp.repository.entity.Store;
import com.erp.repository.entity.StoreMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
        Pageable pageable = PageRequest.of(0, 10);
        System.out.println(storeMenuRepository.findStoreMenuForStore(1L,null,null,null,pageable).getContent());
    }

    @Test
    void findSellingMenuByMenuNameTest(){
        Pageable pageable = PageRequest.of(1, 10);
        System.out.println(storeMenuRepository.findStoreMenu(null,"피자",null,null,pageable).getContent());
    }


    @Test
    void findSellingMenuByStoreNameAndSalesStatusTest() {
        Pageable pageable = PageRequest.of(0, 10);
        System.out.println(storeMenuRepository.findStoreMenu("가산1호점",null,"판매중",null,pageable).getContent());
    }

    @Test
    void findSellingMenuByStoreNameAndMenuCategoryTest() {
        Pageable pageable = PageRequest.of(0, 10);
        System.out.println(storeMenuRepository.findStoreMenu("가산1호점",null,null,"피자",pageable).getContent());
    }

    @Test
    void findSellingMenuByStoreNameTest(){
        Pageable pageable = PageRequest.of(0, 10);
        System.out.println(storeMenuRepository.findStoreMenu("가산1호점",null,null,null,pageable).getContent());
    }

    @Test
    public void setSalesStatusTest() {
        int result = storeMenuRepository.setSalesStatus(40L, "판매중");
        if (result == 0) {
            throw new IllegalArgumentException("해당 store_menu_no 없음: " + 40L);
        }
    }


}
