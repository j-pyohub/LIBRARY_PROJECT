package com.erp.repository;

import com.erp.dto.StoreItemDTO;
import com.erp.repository.entity.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem, Long> {

    List<StoreItem> findByStoreNo(Long storeNo);

    List<StoreItem> findByStoreNoAndItemNo(Long storeNo, Long itemNo);

    /**
     * 1) 매장 전체 재고 현황 조회
     */
    @Query("""
        SELECT new com.erp.dto.StoreItemDTO(
            si.storeItemNo,
            s.storeNo,
            s.storeName,
            i.itemNo,
            i.itemCode,
            i.itemName,
            i.itemCategory,
            COALESCE(si.managerLimit, si.storeLimit),
            CASE
                WHEN si.managerLimit IS NOT NULL THEN 'MANAGER'
                WHEN si.storeLimit IS NOT NULL THEN 'STORE'
                ELSE 'NONE'
            END,
            COALESCE(ss.currentQuantity, 0),
            i.stockUnit
        )
        FROM StoreItem si
        JOIN Store s ON s.storeNo = si.storeNo
        JOIN Item i ON i.itemNo = si.itemNo
        LEFT JOIN StoreStock ss
            ON ss.storeStockNo = (
                SELECT MAX(ss2.storeStockNo)
                FROM StoreStock ss2
                WHERE ss2.storeItemNo = si.storeItemNo
            )
        WHERE si.storeNo = :storeNo
          AND i.delDate IS NULL
        ORDER BY i.itemCategory, i.itemName
        """)
    List<StoreItemDTO> findStoreItemsByStoreNo(@Param("storeNo") Long storeNo);

    /**
     * 2) 카테고리 필터
     */
    @Query("""
        SELECT new com.erp.dto.StoreItemDTO(
            si.storeItemNo,
            s.storeNo,
            s.storeName,
            i.itemNo,
            i.itemCode,
            i.itemName,
            i.itemCategory,
            COALESCE(si.managerLimit, si.storeLimit),
            CASE
                WHEN si.managerLimit IS NOT NULL THEN 'MANAGER'
                WHEN si.storeLimit IS NOT NULL THEN 'STORE'
                ELSE 'NONE'
            END,
            COALESCE(ss.currentQuantity, 0),
            i.stockUnit
        )
        FROM StoreItem si
        JOIN Store s ON s.storeNo = si.storeNo
        JOIN Item i ON i.itemNo = si.itemNo
        LEFT JOIN StoreStock ss
            ON ss.storeStockNo = (
                SELECT MAX(ss2.storeStockNo)
                FROM StoreStock ss2
                WHERE ss2.storeItemNo = si.storeItemNo
            )
        WHERE si.storeNo = :storeNo
          AND i.delDate IS NULL
          AND i.itemCategory = :category
        ORDER BY i.itemCategory, i.itemName
        """)
    List<StoreItemDTO> findStoreItemsByCategory(@Param("storeNo") Long storeNo,
                                                @Param("category") String category);

    /**
     * 3) 품목명 검색
     */
    @Query("""
        SELECT new com.erp.dto.StoreItemDTO(
            si.storeItemNo,
            s.storeNo,
            s.storeName,
            i.itemNo,
            i.itemCode,
            i.itemName,
            i.itemCategory,
            COALESCE(si.managerLimit, si.storeLimit),
            CASE
                WHEN si.managerLimit IS NOT NULL THEN 'MANAGER'
                WHEN si.storeLimit IS NOT NULL THEN 'STORE'
                ELSE 'NONE'
            END,
            COALESCE(ss.currentQuantity, 0),
            i.stockUnit
        )
        FROM StoreItem si
        JOIN Store s ON s.storeNo = si.storeNo
        JOIN Item i ON i.itemNo = si.itemNo
        LEFT JOIN StoreStock ss
            ON ss.storeStockNo = (
                SELECT MAX(ss2.storeStockNo)
                FROM StoreStock ss2
                WHERE ss2.storeItemNo = si.storeItemNo
            )
        WHERE si.storeNo = :storeNo
          AND i.delDate IS NULL
          AND i.itemName LIKE %:itemName%
        ORDER BY i.itemCategory, i.itemName
        """)
    List<StoreItemDTO> findStoreItemsByItemName(@Param("storeNo") Long storeNo,
                                                @Param("itemName") String itemName);

    /**
     * 4) 품목코드 검색
     */
    @Query("""
        SELECT new com.erp.dto.StoreItemDTO(
            si.storeItemNo,
            s.storeNo,
            s.storeName,
            i.itemNo,
            i.itemCode,
            i.itemName,
            i.itemCategory,
            COALESCE(si.managerLimit, si.storeLimit),
            CASE
                WHEN si.managerLimit IS NOT NULL THEN 'MANAGER'
                WHEN si.storeLimit IS NOT NULL THEN 'STORE'
                ELSE 'NONE'
            END,
            COALESCE(ss.currentQuantity, 0),
            i.stockUnit
        )
        FROM StoreItem si
        JOIN Store s ON s.storeNo = si.storeNo
        JOIN Item i ON i.itemNo = si.itemNo
        LEFT JOIN StoreStock ss
            ON ss.storeStockNo = (
                SELECT MAX(ss2.storeStockNo)
                FROM StoreStock ss2
                WHERE ss2.storeItemNo = si.storeItemNo
            )
        WHERE si.storeNo = :storeNo
          AND i.delDate IS NULL
          AND i.itemCode LIKE %:itemCode%
        ORDER BY i.itemCategory, i.itemName
        """)
    List<StoreItemDTO> findStoreItemsByItemCode(@Param("storeNo") Long storeNo,
                                                @Param("itemCode") String itemCode);
}
