package com.erp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuIngredientTest {
    @Autowired
    MenuIngredientRepository menuIngredientRepository;

    @Test
    void removeMenuIngredientTest(){
        menuIngredientRepository.deleteById(90L);
    }
}
