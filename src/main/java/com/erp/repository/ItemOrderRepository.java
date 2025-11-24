package com.erp.repository;

import com.erp.repository.entity.ItemOrder;
import com.erp.repository.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {

    Page<ItemOrder> findByRequestDatetimeBetween(LocalDateTime requestDatetime, LocalDateTime end, Pageable pageable);

    @Query("select o from ItemOrder o where function('DAYOFWEEK', o.requestDatetime) = :day")
    Page<ItemOrder> findByRequestDatetimeDay(@Param("day") int day, Pageable pageable);

    Page<ItemOrder> findByStoreNo(Store storeNo, Pageable pageable);

    Page<ItemOrder> findByItemOrderStatus(String status, Pageable pageable);

    List<ItemOrder> findByItemOrderStatusAndStoreNo(String status, Store storeNo);

    ItemOrder findByItemOrderNo(long l);
}
