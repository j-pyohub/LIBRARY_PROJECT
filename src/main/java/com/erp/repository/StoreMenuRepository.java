package com.erp.repository;

import com.erp.repository.entity.StoreMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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
}
