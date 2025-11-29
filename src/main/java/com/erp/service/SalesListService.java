//package com.erp.service;
//
//import com.erp.dto.SalesListDTO;
//import com.erp.repository.SalesOrderRepository;
//import com.erp.repository.StoreSalesRepository;
//import com.erp.repository.entity.StoreSales;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class SalesListService {
//
//    private final StoreSalesRepository storeSalesRepository;
//    private final SalesOrderRepository salesOrderRepository;
//
//    public Page<SalesListDTO> getSalesList(LocalDate startDate,
//                                           LocalDate endDate,
//                                           String storeName,
//                                           Pageable pageable) {
//
//        // 1) 기간 내 전체 StoreSales 불러오기
//        List<StoreSales> list =
//                storeSalesRepository.findBySalesDateBetween(startDate, endDate);
//
//        // 2) 지점명 검색 조건 필터링
//        if (storeName != null && !storeName.isBlank()) {
//            list = list.stream()
//                    .filter(s -> s.getStore().getStoreName().contains(storeName))
//                    .toList();
//        }
//
//        // 3) StoreSales → DTO 변환 + 주문수 계산
//        List<SalesListDTO> dtoList = list.stream()
//                .map(sales -> {
//                    Long storeNo = sales.getStore().getStoreNo();
//
//                    // 주문수 구하기
//                    int orderCount = salesOrderRepository.countOrders(
//                            storeNo,
//                            Date.valueOf(sales.getSalesDate())
//                    );
//
//                    // 매출액
//                    int salesAmount = sales.getSalesPrice();
//
//                    return new SalesListDTO(
//                            storeNo,
//                            sales.getStore().getStoreName(),
//                            sales.getStore().getAddress(),
//                            orderCount,
//                            salesAmount,
//                            sales.getSalesDate(),
//                            null     // growthRate는 아래에서 계산
//                    );
//                })
//                .toList();
//
//        // 4) 전일 대비 growthRate 계산 (“5.3% 상승” 등)
//        for (SalesListDTO dto : dtoList) {
//
//            LocalDate prevDay = dto.getSalesDate().minusDays(1);
//
//            // 전일 매출 조회
//            List<StoreSales> prevList =
//                    storeSalesRepository.findByStore_StoreNoAndSalesDateBetween(
//                            dto.getStoreNo(),
//                            prevDay,
//                            prevDay
//                    );
//
//            if (!prevList.isEmpty()) {
//                int prevAmount = prevList.get(0).getSalesPrice();
//                int todayAmount = dto.getSalesAmount();
//
//                double rate = ((double) (todayAmount - prevAmount) / prevAmount) * 100;
//
//                dto.setGrowthRate(
//                        String.format("%.1f%% %s",
//                                Math.abs(rate),
//                                rate >= 0 ? "상승" : "하락")
//                );
//            } else {
//                // 전일 매출 없음
//                dto.setGrowthRate("-");
//            }
//        }
//
//        // 5) 페이지네이션 수동 계산
//        int start = (int) pageable.getOffset();
//        int end = Math.min(start + pageable.getPageSize(), dtoList.size());
//
//        List<SalesListDTO> pageContent =
//                (start > end) ? List.of() : dtoList.subList(start, end);
//
//        return new PageImpl<>(pageContent, pageable, dtoList.size());
//    }
//}
