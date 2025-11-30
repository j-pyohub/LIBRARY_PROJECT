package com.erp.service;

import com.erp.dto.SalesOrderDTO;
import com.erp.repository.SalesOrderRepository;
import com.erp.repository.StoreOrderDetailRepository;
import com.erp.repository.entity.SalesOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;
    private final StoreOrderDetailRepository storeOrderDetailRepository;

    @Transactional
    public List<SalesOrderDTO> getSalesOrderList() {

        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();
        List<SalesOrderDTO> salesOrderDTO = new ArrayList<>();
        for (SalesOrder order : salesOrderList) {
            Long orderNo = order.getSalesOrderNo();
            SalesOrderDTO countDto = storeOrderDetailRepository.countSalesOrder(orderNo);
            countDto.setStoreNo(order.getStore().getStoreNo());
            countDto.setStoreName(order.getStore().getStoreName());
            salesOrderDTO.add(countDto);
        }
        return salesOrderDTO;
    }
}
