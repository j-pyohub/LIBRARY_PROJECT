package com.oopsw.erp_project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuRepositoryTest {
    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void getMenu() {
        System.out.println(menuRepository.findAll());
    }
}
