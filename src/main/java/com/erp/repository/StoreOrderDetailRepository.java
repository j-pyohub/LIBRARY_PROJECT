package com.erp.repository;

import com.erp.repository.dto.SalesOrderDTO;
import com.erp.repository.dto.StoreDailyMenuSalesDTO;
import com.erp.repository.entity.StoreOrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;
import java.util.List;

public interface StoreOrderDetailRepository extends JpaRepository<StoreOrderDetail, Long> {
    @Query("""
    SELECT new com.erp.repository.dto.SalesOrderDTO(
       o.salesOrderNo,
       SUM(d.menuCount),
       SUM(d.menuCount * d.menuPrice)
   )
       FROM StoreOrderDetail d
       JOIN d.salesOrder o
       WHERE o.salesOrderNo = :orderNo
       GROUP BY o.salesOrderNo
""")
    SalesOrderDTO countSalesOrder(@Param("orderNo") Long orderNo);

    @Query("""
        select new com.erp.repository.dto.StoreDailyMenuSalesDTO(
            so.store.storeName,
            m.menuCategory,
            m.menuName,
            m.size,
            sod.menuCount,
            (sod.menuPrice * sod.menuCount)
        )
        from SalesOrder so
            join so.orderDetails sod
            join sod.storeMenu sm
            join sm.menu m
        where so.salesOrderDatetime >= :start
          and so.salesOrderDatetime < :end
        order by so.store.storeName asc,
                 (sod.menuPrice * sod.menuCount) desc
        """)
    List<StoreDailyMenuSalesDTO> findDailyMenuSales(@org.springframework.data.repository.query.Param("start") LocalDateTime startDate, @org.springframework.data.repository.query.Param("end")  LocalDateTime endDate);

    @Query("""
    select new com.erp.repository.dto.StoreDailyMenuSalesDTO(
        so.store.storeName,
        m.menuCategory,
        m.menuName,
        m.size,
        sod.menuCount,
        (sod.menuPrice * sod.menuCount)
    )
    from SalesOrder so
        join so.orderDetails sod
        join sod.storeMenu sm
        join sm.menu m
    where so.store.storeNo = :storeNo       
      and so.salesOrderDatetime >= :start
      and so.salesOrderDatetime < :end
    order by (sod.menuPrice * sod.menuCount) desc
    """)
    List<StoreDailyMenuSalesDTO> findDailyMenuSalesByStore(@org.springframework.data.repository.query.Param("storeNo") Long storeNo,
                                                           @org.springframework.data.repository.query.Param("start") LocalDateTime start,
                                                           @org.springframework.data.repository.query.Param("end") LocalDateTime end
    );

}
