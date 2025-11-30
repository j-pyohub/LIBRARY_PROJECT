package com.erp.service;

import com.erp.dto.SalesOrderDTO;
import com.erp.repository.SalesOrderRepository;
import com.erp.repository.StoreOrderDetailRepository;
import com.erp.repository.entity.SalesOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;
    private final StoreOrderDetailRepository storeOrderDetailRepository;

    @Transactional(readOnly = true)
    public Page<SalesOrderDTO> getSalesOrderList(Integer pageNo) {
        //Specification<SalesOrder> spec = Specification.where(null);
        return salesOrderRepository.getSalesOrderList(
                PageRequest.of(pageNo, 10)
        );
    }

}
