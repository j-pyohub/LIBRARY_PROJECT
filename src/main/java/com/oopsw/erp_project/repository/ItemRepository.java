package com.oopsw.erp_project.repository;

import com.oopsw.erp_project.vo.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository // 써도 되고 안써도 되고
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByItemCode(String itemCode);

    List<Item> findByItemName(String itemName);

    List<Item> findByIngredientName(String ingredientName);
}
