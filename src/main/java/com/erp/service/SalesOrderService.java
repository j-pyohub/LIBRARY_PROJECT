package com.erp.service;

import com.erp.dto.SalesOrderDTO;
import com.erp.repository.SalesOrderRepository;
import com.erp.repository.StoreOrderDetailRepository;
import com.erp.repository.entity.SalesOrder;
import com.erp.specification.SalesOrderSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;

    @Transactional(readOnly = true)
    public Page<SalesOrderDTO> getSalesOrderList(Integer pageNo, LocalDate date, String storeName) {
        Specification<SalesOrder> spec = Specification.where(null);

        spec = spec.and(SalesOrderSpec.findByDate(date));
        spec = spec.and(SalesOrderSpec.findByStoreName(storeName));

        PageRequest pageable = PageRequest.of(
                pageNo,
                10,
                Sort.by(Sort.Direction.DESC, "salesOrderNo") // ★ 내림차순 정렬 추가!
        );

        Page<SalesOrder> page = salesOrderRepository.findAll(spec, pageable);

        return page.map(SalesOrderDTO::fromEntity);
    }

}
