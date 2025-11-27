package com.erp.repository;

import com.erp.repository.entity.StoreSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface StoreSalesRepository extends JpaRepository<StoreSales,Long> {
    List<StoreSales> findBySalesDateBetween(LocalDate startDate, LocalDate endDate);
    List<StoreSales> findByStore_StoreNoAndSalesDateBetween(Long storeNo, LocalDate startDate, LocalDate endDate);
}
