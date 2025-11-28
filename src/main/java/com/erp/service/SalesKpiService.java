//package com.erp.service;
//
//import com.erp.dao.StoreDAO;
//import com.erp.dto.KpiDTO;
//import com.erp.repository.SalesOrderRepository;
//import com.erp.repository.StoreSalesRepository;
//import com.erp.repository.entity.StoreSales;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.YearMonth;
//
//@Service
//@RequiredArgsConstructor
//public class KpiService {
//
//    private final StoreSalesRepository storeSalesRepository;
//    private final SalesOrderRepository salesOrderRepository;
//    private final StoreDAO storeDAO;
//
//    // KPI 메인 서비스
//    public KpiDTO getKpi(String type, LocalDate startDate, LocalDate endDate) {
//
//        // 1. 전체 매출 합계
//        int totalSales = sumSales(startDate, endDate);
//
//        // 2. 판매 수량 합계
//        LocalDateTime startDt = startDate.atStartOfDay();
//        LocalDateTime endDt   = endDate.atTime(23, 59, 59);
//
//        Integer totalMenuCount = salesOrderRepository.getTotalMenuCount(startDt, endDt);
//        if (totalMenuCount == null) totalMenuCount = 0;
//
//        // 3. 직영점 수
//        int storeCount = storeDAO.countStores();
//
//        // 4. 평균 직영점 매출
//        double avgStoreSales = storeCount == 0
//                ? 0.0
//                : (double) totalSales / storeCount;
//
//        // 5. 성장률 계산 (전일/전주/전월/전년 대비)
//        double growthRate = calcGrowthRate(type, startDate, endDate);
//
//        return KpiDTO.builder()
//                .totalSales(totalSales)
//                .totalMenuCount(totalMenuCount)
//                .avgStoreSales(avgStoreSales)
//                .growthRate(growthRate)
//                .build();
//    }
//
//    // 매출 합계 계산
//    private int sumSales(LocalDate start, LocalDate end) {
//        return storeSalesRepository.findBySalesDateBetween(start, end)
//                .stream()
//                .mapToInt(StoreSales::getSalesPrice)
//                .sum();
//    }
//
//    // 성장률 계산
//    private double calcGrowthRate(String type, LocalDate startDate, LocalDate endDate) {
//
//        int current = sumSales(startDate, endDate);
//        int previous = 0;
//
//        switch (type) {
//
//            case "day":
//                LocalDate prevDay = startDate.minusDays(1);
//                previous = sumSales(prevDay, prevDay);
//                break;
//
//            case "week":
//                LocalDate prevWeekStart = startDate.minusWeeks(1);
//                LocalDate prevWeekEnd   = endDate.minusWeeks(1);
//                previous = sumSales(prevWeekStart, prevWeekEnd);
//                break;
//
//            case "month":
//                YearMonth ym = YearMonth.from(startDate);
//                YearMonth prevYm = ym.minusMonths(1);
//                previous = sumSales(prevYm.atDay(1), prevYm.atEndOfMonth());
//                break;
//
//            case "year":
//                int prevYear = startDate.getYear() - 1;
//                previous = sumSales(
//                        LocalDate.of(prevYear, 1, 1),
//                        LocalDate.of(prevYear, 12, 31)
//                );
//                break;
//
//            default:
//                throw new IllegalArgumentException("invalid type");
//        }
//
//        if (previous == 0) return 0.0;
//
//        return ((double) (current - previous) / previous) * 100.0;
//    }
//}
