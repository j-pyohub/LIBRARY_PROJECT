package com.erp.repository;

import com.erp.repository.entity.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem, Long> {
    List<StoreItem> findAll();

    List<StoreItem> findByStoreNo(Long storeNo);

    List<StoreItem> findByStoreNoAndItemNo(Long storeNo, Long itemNo);

    // 카테고리로 조회
    @Query("""
        SELECT si 
        FROM StoreItem si
        JOIN Item i ON si.itemNo = i.itemNo
        WHERE si.storeNo = :storeNo
          AND i.itemCategory = :category
    """)
    List<StoreItem> findByCategory(@Param("storeNo") Long storeNo,
                                   @Param("category") String category);

    // 품목명 검색

    @Query("""
        SELECT si 
        FROM StoreItem si
        JOIN Item i ON si.itemNo = i.itemNo
        WHERE si.storeNo = :storeNo
          AND i.itemName LIKE %:name%
    """)
    List<StoreItem> findByItemName(@Param("storeNo") Long storeNo,
                                   @Param("name") String name);


    // 품목코드 검색
    @Query("""
        SELECT si 
        FROM StoreItem si
        JOIN Item i ON si.itemNo = i.itemNo
        WHERE si.storeNo = :storeNo
          AND i.itemCode LIKE %:code%
    """)
    List<StoreItem> findByItemCode(@Param("storeNo") Long storeNo,
                                   @Param("code") String code);
}
