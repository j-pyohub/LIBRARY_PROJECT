package com.erp.repository;

import com.erp.repository.entity.ItemOrder;
import com.erp.repository.entity.ItemOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface ItemOrderDetailRepository extends JpaRepository<ItemOrderDetail, Long> {

    List<ItemOrderDetail> findByItemOrderNo_ItemOrderNo(ItemOrder itemOrderNo);

    boolean existsByItemOrderNo_ItemOrderNoAndReceiveDatetimeIsNull(Long itemOrderNo);


//    boolean existsByItemOrderNo_ItemOrderNoAndReceiveDatetimeIsNull(Long itemOrderNo);
}
