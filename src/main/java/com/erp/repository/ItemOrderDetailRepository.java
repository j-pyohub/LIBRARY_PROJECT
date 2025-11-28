package com.erp.repository;

import com.erp.repository.entity.ItemOrder;
import com.erp.repository.entity.ItemOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOrderDetailRepository extends JpaRepository<ItemOrderDetail, Long> {

    List<ItemOrderDetail> findByItemOrderNo(ItemOrder itemOrderNo);

}
