package com.erp.service;

import com.erp.dto.SalesChartDTO;
import com.erp.dto.TotalStoreSalesDTO;
import com.erp.repository.StoreSalesRepository;
import com.erp.repository.entity.StoreSales;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final StoreSalesRepository storeSalesRepository;



    @Transactional
    public List<TotalStoreSalesDTO> getTotalStoreSales() {

        LocalDate endDate = LocalDate.now().minusDays(1);
        LocalDate startDate = endDate.minusDays(30);

        List<StoreSales> salesList = storeSalesRepository.findBySalesDateBetween(startDate, endDate);

        Map<String, Integer> grouped =
                salesList.stream()
                        .collect(Collectors.groupingBy(
                                s -> s.getStore().getStoreName(),
                                Collectors.summingInt(StoreSales::getSalesPrice)
                        ));

        return grouped.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .map(e -> new TotalStoreSalesDTO(e.getKey(), e.getValue()))
                .toList();
    }

    public SalesChartDTO getSalesChart(LocalDate startDate, LocalDate endDate, String type) {

        List<StoreSales> list = storeSalesRepository.findBySalesDateBetween(startDate, endDate);

        Map<String, Integer> grouped;

        switch (type) {
            case "day":
                grouped = groupByDay(list);
                break;
            case "week":
                grouped = groupByWeek(list);
                break;
            case "month":
                grouped = groupByMonth(list);
                break;
            case "year":
                grouped = groupByYear(list);
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
        List<String> labels = grouped.keySet()
                .stream()
                .sorted()
                .toList();
        List<Integer> values = labels.stream()
                .map(grouped::get)
                .toList();

        return SalesChartDTO.builder()
                .labels(labels)
                .values(values)
                .build();
    }








    private Map<String, Integer> groupByDay(List<StoreSales> list) {
        return list.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getSalesDate().toString(),  // yyyy-MM-dd
                        Collectors.summingInt(StoreSales::getSalesPrice)
                ));
    }
    private Map<String, Integer> groupByWeek(List<StoreSales> list) {
        WeekFields wf = WeekFields.ISO;
        return list.stream()
                .collect(Collectors.groupingBy(
                        s -> {
                            LocalDate d = s.getSalesDate();
                            int year = d.getYear();
                            int week = d.get(wf.weekOfYear());
                            return year + "-W" + week;   // 예: 2025-W7
                        },
                        Collectors.summingInt(StoreSales::getSalesPrice)
                ));
    }

    private Map<String, Integer> groupByMonth(List<StoreSales> list) {
        return list.stream()
                .collect(Collectors.groupingBy(
                        s -> YearMonth.from(s.getSalesDate()).toString(),  // 2025-01
                        Collectors.summingInt(StoreSales::getSalesPrice)
                ));
    }

    private Map<String, Integer> groupByYear(List<StoreSales> list) {
        return list.stream()
                .collect(Collectors.groupingBy(
                        s -> String.valueOf(s.getSalesDate().getYear()),   // 2025
                        Collectors.summingInt(StoreSales::getSalesPrice)
                ));
    }

}


