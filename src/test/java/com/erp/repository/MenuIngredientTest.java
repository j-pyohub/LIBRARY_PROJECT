package com.erp.repository;

import com.erp.repository.entity.Item;
import com.erp.repository.entity.Menu;
import com.erp.repository.entity.MenuIngredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuIngredientTest {
    @Autowired
    MenuIngredientRepository menuIngredientRepository;

    @Test
    void addMenuIngredientTest(){
        Item item = Item.builder().itemNo(43L).build();
        Menu menu = Menu.builder().menuNo(21L).build();;
        Integer ingredientQuantity = 50;
        MenuIngredient newIngredient = MenuIngredient.builder().menu(menu).item(item).ingredientQuantity(ingredientQuantity).build();
        menuIngredientRepository.save(newIngredient);
    }

    @Test
    void setMenuIngredientTest() {
        long menuIngredientNo = 92;
        Integer newQuantity = 50;
        MenuIngredient menuIngredient =  menuIngredientRepository.findById(menuIngredientNo).get();
        menuIngredient.setIngredientQuantity(newQuantity);

        menuIngredientRepository.save(menuIngredient);
    }

    @Test
    void removeMenuIngredientTest(){
        menuIngredientRepository.deleteById(90L);
    }
}
