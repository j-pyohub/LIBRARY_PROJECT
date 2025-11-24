package com.erp.repository;

import com.erp.repository.entity.Manager;
import com.erp.repository.entity.Menu;
import com.erp.repository.entity.Store;
import com.erp.repository.entity.StoreMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StoreRepositoryTest {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private StoreMenuRepository storeMenuRepository;

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
    @Test
    @Transactional
    public void getStoreByStoreStatus(){
        System.out.println(storeRepository.findByStoreStatus("오픈준비"));

    }
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void addStore() {
//        if(managerRepository.existsById("storeManager5"))
//            throw new RuntimeException("아이디 중복X");
//        Store store = storeRepository.save(Store.builder().storeStatus("영업중").manager(managerRepository.save(Manager.builder().managerId("storeManager5").pw("123")
//                                                .email("storeManager2@pizza.com").managerName("김동글")
//                                                .phoneNumber("010-1212-2222").role("ROLE_STORE").build()))
//                                            .storeName("염창점").address("서울 강서구 양천로 683")
//                                            .latitude("37.5509706499685 ").longitude("126.872328876047")
//                                            .storePhoneNumber("02-4433-2211").openTime("11:00").closeTime("22:00").menuStopRole("N").build());
//
//        List<Menu> releasedMenus = menuRepository.findByReleaseStatusAndDelDateIsNull("출시 중");
//
//        List<StoreMenu> storeMenus = new ArrayList<>();
//        for (Menu menu : releasedMenus) {
//            StoreMenu sm = StoreMenu.builder()
//                    .store(store)
//                    .menu(menu)
//                    .salesStatus("판매중단")  // 기본값 설정
//                    .build();
//
//            storeMenus.add(sm);
//        }
//        storeMenuRepository.saveAll(storeMenus);
//        System.out.println(store);
//    }

    @Test
    @Transactional
    @Rollback(false)
    public void addStoreMenuTest() {

        Store store = storeRepository.findById(17L).get();

        List<Menu> releasedMenus = menuRepository.findByReleaseStatusAndDelDateIsNull("출시 중");

        List<StoreMenu> storeMenus = new ArrayList<>();
        for (Menu menu : releasedMenus) {
            StoreMenu sm = StoreMenu.builder()
                    .store(store)
                    .menu(menu)
                    .salesStatus("판매중단")  // 기본값 설정
                    .build();

            storeMenus.add(sm);
        }
        storeMenuRepository.saveAll(storeMenus);
        System.out.println(store);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void setStore() {
        Store store = storeRepository.findById(11L).get();
        System.out.println(store);
        store.setStorePhoneNumber("02-3344-1122");
    }


}
