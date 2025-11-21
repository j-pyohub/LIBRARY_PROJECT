package com.erp.dao;

import com.erp.repository.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemDAO {

    // 1) 등록
    int addItem(Item item);

    // 2) 수정
    int setItem(Item item);

    // 3) 삭제(del_date 업데이트)
    int removeItem(@Param("itemNo") Long itemNo);

    // 4) 전체 목록 조회
    List<Item> getItemList();

    // 5) 카테고리 검색
    List<Item> getByCategory(@Param("itemCategory") String itemCategory);

    // 6) 품목명 검색
    List<Item> getByItemName(@Param("itemName") String itemName);

    // 7) 품목코드 검색
    List<Item> getByItemCode(@Param("itemCode") String itemCode);

    // 8) 재료명 검색
    List<Item> getByIngredient(@Param("ingredientName") String ingredientName);

    // 9) 상세 조회
    Item getItemDetail(@Param("itemNo") Long itemNo);

}
