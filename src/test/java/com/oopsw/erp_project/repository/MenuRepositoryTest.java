package com.oopsw.erp_project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuRepositoryTest {
    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void getAllMenu() {
        System.out.println(menuRepository.findAll());
    }

    @Test
    public void getMenuByCategory() {
        System.out.println(menuRepository.findByMenuCategory("음료"));
    }

    @Test
    public void getMenuByReleaseStatus() {
        System.out.println(menuRepository.findByReleaseStatus("출시 예정"));
    }

}
