package com.erp.repository;

import com.erp.repository.dto.StoreSellingMenuDTO;
import com.erp.repository.entity.StoreMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StoreMenuRepository extends JpaRepository<StoreMenu, Long> {
        @Modifying
        @Transactional
        @Query("""
                UPDATE StoreMenu sm 
                SET sm.salesStatus = :status
                WHERE sm.storeMenuNo = :storeMenuNo
        """)
        int setSalesStatus(@Param("storeMenuNo") Long storeMenuNo,
                              @Param("status") String salesStatus);

        @Query("""
        SELECT new com.erp.repository.dto.StoreSellingMenuDTO(
            s.storeName,
            m.menuCode,
            m.menuName,
            m.size,
            m.menuPrice,
            sm.salesStatus
        )
        FROM StoreMenu sm
            JOIN sm.store s
            JOIN sm.menu m
        WHERE s.storeNo = :storeNo
    """)
        List<StoreSellingMenuDTO> findSellingMenuByStoreNo(@Param("storeNo") Long storeNo);

        @Query("""
    SELECT new com.erp.repository.dto.StoreSellingMenuDTO(
        s.storeName,
        m.menuCode,
        m.menuName,
        m.size,
        m.menuPrice,
        sm.salesStatus
    )
    FROM StoreMenu sm
        JOIN sm.store s
        JOIN sm.menu m
    WHERE m.menuName LIKE %:menuName%
""")
        List<StoreSellingMenuDTO> findSellingMenuByMenuName(@Param("menuName") String menuName);
}
