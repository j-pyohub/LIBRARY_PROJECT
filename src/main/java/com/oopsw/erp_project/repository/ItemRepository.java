package com.oopsw.erp_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemCodeContaining(String code);

    List<Item> findByItemNameContaining(String 파);

    List<Item> findByIngredientNameContaining(String 마늘);
}
