package com.erp.service;

import com.erp.repository.SalesOrderRepository;
import com.erp.repository.StoreOrderDetailRepository;
import com.erp.repository.entity.SalesOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreOrderService {
    private final SalesOrderRepository salesOrderRepository;
    private final StoreOrderDetailRepository storeOrderDetailRepository;

    public List<SalesOrder> getSalesOrder() {

        return salesOrderRepository.findAll();
    }
}
