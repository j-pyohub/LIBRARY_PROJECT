package com.erp.repository;

import com.erp.repository.entity.StoreSales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StoreSalesRepository extends JpaRepository<StoreSales,Long> {
    List<StoreSales> findBySalesDateBetween(LocalDate startDate, LocalDate endDate);
}
