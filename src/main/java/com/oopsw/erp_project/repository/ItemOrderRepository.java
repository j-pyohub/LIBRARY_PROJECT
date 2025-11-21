package com.oopsw.erp_project.repository;

import com.oopsw.erp_project.repository.entity.ItemOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ItemOrderRepository extends CrudRepository<ItemOrder, Long> {

    List<ItemOrder> findByRequestDatetimeBetween(LocalDateTime requestDatetime, LocalDateTime end);

    @Query("select o from ItemOrder o where function('DAYOFWEEK', o.requestDatetime) = :day")
    List<ItemOrder> findByRequestDatetimeDay(@Param("day") int day);

    List<ItemOrder> findByStoreNo(Long storeNo);

    List<ItemOrder> findByItemOrderStatus(String status);

    List<ItemOrder> findByItemOrderStatusAndStoreNo(String status, long storeNo);
}