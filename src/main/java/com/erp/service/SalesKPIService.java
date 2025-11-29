package com.erp.service;

import com.erp.dao.StoreDAO;
import com.erp.dto.KPIDTO;
import com.erp.repository.SalesOrderRepository;
import com.erp.repository.StoreSalesRepository;
import com.erp.repository.entity.StoreSales;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor
public class SalesKPIService {

    private final StoreSalesRepository storeSalesRepository;
    private final SalesOrderRepository salesOrderRepository;
    private final StoreDAO storeDAO;

    public KPIDTO getKpi(String type, LocalDate startDate, LocalDate endDate) {


        int totalSales = sumSales(startDate, endDate);


        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime   = endDate.atTime(23, 59, 59);

        Integer totalMenuCount = salesOrderRepository.getTotalMenuCount(startDateTime, endDateTime);
        if (totalMenuCount == null) totalMenuCount = 0;

        int storeCount = storeDAO.countStores();


        int avgStoreSales = storeCount == 0
                ? 0
                : (int) Math.round((double) totalSales / storeCount);



        double growthRate = calcGrowthRate(type, startDate, endDate);

        return KPIDTO.builder()
                .totalSales(totalSales)
                .totalMenuCount(totalMenuCount)
                .avgStoreSales(avgStoreSales)
                .growthRate(growthRate)
                .build();
    }


    private int sumSales(LocalDate startDate, LocalDate endDate) {
        return storeSalesRepository.findBySalesDateBetween(startDate, endDate)
                .stream()
                .mapToInt(StoreSales::getSalesPrice)
                .sum();
    }


    private double calcGrowthRate(String type, LocalDate startDate, LocalDate endDate) {

        int current = sumSales(startDate, endDate);
        int previous = 0;

        switch (type) {

            case "day":
                LocalDate prevDay = startDate.minusDays(1);
                previous = sumSales(prevDay, prevDay);
                break;

            case "week":
                LocalDate prevWeekStart = startDate.minusWeeks(1);
                LocalDate prevWeekEnd   = endDate.minusWeeks(1);
                previous = sumSales(prevWeekStart, prevWeekEnd);
                break;

            case "month":
                YearMonth ym = YearMonth.from(startDate);
                YearMonth prevYm = ym.minusMonths(1);
                previous = sumSales(prevYm.atDay(1), prevYm.atEndOfMonth());
                break;

            case "year":
                int prevYear = startDate.getYear() - 1;
                previous = sumSales(
                        LocalDate.of(prevYear, 1, 1),
                        LocalDate.of(prevYear, 12, 31)
                );
                break;

            default:
                throw new IllegalArgumentException("invalid type");
        }

        if (previous == 0) return 0.0;

        return ((double) (current - previous) / previous) * 100.0;
    }
}
