package com.erp.service;

import com.erp.dto.SalesOrderDTO;
import com.erp.dto.SalesOrderDetailDTO;
import com.erp.repository.SalesOrderRepository;
import com.erp.repository.StoreOrderDetailRepository;
import com.erp.repository.entity.SalesOrder;
import com.erp.repository.entity.StoreOrderDetail;
import com.erp.specification.SalesOrderSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;
    private final StoreOrderDetailRepository storeOrderDetailRepository;

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

    @Transactional
    public Map<String, Object> getSalesOrderDetail(Long salesOrderNo) {
        List<StoreOrderDetail> list = storeOrderDetailRepository.getStoreOrderDetail(salesOrderNo);

        if (list.isEmpty()) {
            return null;
        }
        SalesOrder first = list.get(0).getSalesOrder();

       List<SalesOrderDetailDTO> menuList = list.stream().map(
               storeOrderDetail -> new SalesOrderDetailDTO(
                       storeOrderDetail.getStoreMenu().getMenu().getMenuName(),
                       storeOrderDetail.getStoreMenu().getMenu().getSize(),
                       storeOrderDetail.getMenuPrice(),
                       storeOrderDetail.getMenuCount(),
                       storeOrderDetail.getMenuPrice() * storeOrderDetail.getMenuCount()
               )).toList();

       int totalPrice = menuList.stream()
               .mapToInt(SalesOrderDetailDTO::getTotalPrice)
               .sum();

       return Map.of(
               "salesOrderNo", first.getSalesOrderNo(),
               "salesOrderDatetime", first.getSalesOrderDatetime(),
               "storeName", first.getStore().getStoreName(),
               "menuList", menuList,
               "totalPrice", totalPrice
       );
    }
}
