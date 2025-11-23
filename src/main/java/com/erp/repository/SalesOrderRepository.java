package com.erp.repository;

import com.erp.repository.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Integer> {
   // List<SalesOrder> findSalesOrderByDate(LocalDate salesOrderDatetime);
}
