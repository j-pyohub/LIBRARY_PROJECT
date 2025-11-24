package com.erp.repository;

import com.erp.repository.entity.StoreStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


import java.util.List;
@Repository
public interface StoreStockRepository extends JpaRepository<StoreStock, Long> {

    // 1) 직영점 전체 변동 기록 조회
    @Query("""
        SELECT ss
        FROM StoreStock ss
        JOIN StoreItem si ON ss.storeItemNo = si.storeItemNo
        WHERE si.storeNo = :storeNo
        ORDER BY ss.storeStockNo DESC
    """)
    List<StoreStock> findAllByStoreNo(@Param("storeNo") Long storeNo);

    // 2) 직영점 + 품목명 검색
    @Query("""
        SELECT ss
        FROM StoreStock ss
        JOIN StoreItem si ON ss.storeItemNo = si.storeItemNo
        JOIN Item itm ON si.itemNo = itm.itemNo
        WHERE si.storeNo = :storeNo
          AND itm.itemName LIKE %:itemName%
        ORDER BY ss.storeStockNo DESC
    """)
    List<StoreStock> findByItemName(@Param("storeNo") Long storeNo,
                                    @Param("itemName") String itemName);

    // 3) 직영점 + 품목코드 검색
    @Query("""
        SELECT ss
        FROM StoreStock ss
        JOIN StoreItem si ON ss.storeItemNo = si.storeItemNo
        JOIN Item itm ON si.itemNo = itm.itemNo
        WHERE si.storeNo = :storeNo
          AND itm.itemCode LIKE %:itemCode%
        ORDER BY ss.storeStockNo DESC
    """)
    List<StoreStock> findByItemCode(@Param("storeNo") Long storeNo,
                                    @Param("itemCode") String itemCode);


    //4)  직영점 + 기간으로 재고 변동 조회
        @Query("""
            SELECT ss
            FROM StoreStock ss
            JOIN StoreItem si ON ss.storeItemNo = si.storeItemNo
            WHERE si.storeNo = :storeNo
              AND ss.changeDatetime BETWEEN :start AND :end
            ORDER BY ss.storeStockNo DESC
        """)
        List<StoreStock> findByStoreNoAndChangeDatetimeBetween(
                @Param("storeNo") Long storeNo,
                @Param("start") java.sql.Timestamp start,
                @Param("end") java.sql.Timestamp end
        );

    // 현재 재고 수량 조회
    StoreStock findFirstByStoreItemNoOrderByStoreStockNoDesc(Long storeItemNo);

    StoreStock findByStoreItemNo(Long storeItemNo);
}



