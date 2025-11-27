package com.erp.repository;

import com.erp.repository.entity.ItemOrder;
import com.erp.repository.entity.ItemOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemOrderDetailRepository extends JpaRepository<ItemOrderDetail, Long> {

    List<ItemOrderDetail> findByItemOrderNo(ItemOrder itemOrderNo);

    boolean existsByItemOrderNoAndReceiveDatetimeIsNull(long itemOrderNo);
}
