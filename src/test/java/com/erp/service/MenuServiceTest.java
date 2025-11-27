package com.erp.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuServiceTest {
    @Autowired
    MenuService menuService;

    @Test
    public void getAllMenuTest(){
        System.out.println(menuService.getAllMenu());
    }

    @Test
    public void getMenuByCategoryTest(){
        System.out.println(menuService.getMenuByCategory("음료"));
    }

    @Test
    public void getMenuByReleaseStatusTest(){
        System.out.println(menuService.getMenuByReleaseStatus("출시 예정"));
    }

    @Test
    public void getMenuByCategoryAndReleaseStatusTest(){
        System.out.println(menuService.getMenuByCategoryAndReleaseStatus("피자", "출시 중단"));
    }

    @Test
    public void getMenuDetailTest(){
        Long menuNo = 1L;
        System.out.println(menuService.getMenuDetail(menuNo));
    }
}
