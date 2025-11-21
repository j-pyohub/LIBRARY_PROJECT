package com.oopsw.erp_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemCode(String itemCode);

    List<Item> findByItemName(String itemName);

    List<Item> findByIngredientName(String ingredientName);
}
