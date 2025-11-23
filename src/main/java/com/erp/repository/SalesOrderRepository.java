package com.erp.repository;

import com.erp.repository.entity.SalesOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

    @Query("""
           SELECT COUNT(o)
           FROM SalesOrder o
           WHERE o.store.storeNo = :storeNo
             AND DATE(o.salesOrderDatetime) = :salesDate
           """)
    int countOrders(@Param("storeNo") Long storeNo, @Param("salesDate") Date salesDate );


    @Query("""
            SELECT o
            FROM SalesOrder o
            WHERE DATE(o.salesOrderDatetime) = :salesDate
""")
    List<SalesOrder> getSalesOrderbyDate(@Param("salesDate") LocalDate salesDate);

    @Query("""
            SELECT o
            FROM SalesOrder o
            where o.store.storeNo = :storeNo
""")
    List<SalesOrder> getSalesOrdersByStore(@Param("storeNo") Long storeNo);
}
