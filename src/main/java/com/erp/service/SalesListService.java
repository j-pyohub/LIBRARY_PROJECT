package com.erp.service;

import com.erp.dto.SalesListDTO;
import com.erp.repository.SalesOrderRepository;
import com.erp.repository.StoreSalesRepository;
import com.erp.repository.entity.StoreSales;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesListService {

    private final StoreSalesRepository storeSalesRepository;

    public Page<SalesListDTO> getSalesList(LocalDate startDate,
                                           LocalDate endDate,
                                           String storeName,
                                           Pageable pageable) {

        Page<SalesListDTO> page =
                storeSalesRepository.findSalesList(startDate, endDate, storeName, pageable);

        List<SalesListDTO> content = page.getContent();

        for (SalesListDTO dto : content) {
            LocalDate prevDay = dto.getSalesDate().minusDays(1);
            List<StoreSales> prevList =
                    storeSalesRepository.findByStore_StoreNoAndSalesDateBetween(
                            dto.getStoreNo(),
                            prevDay,
                            prevDay
                    );
            if (!prevList.isEmpty()) {
                int prevAmount = prevList.get(0).getSalesPrice();
                int todayAmount = dto.getSalesAmount();

                double rate = ((double) (todayAmount - prevAmount) / prevAmount) * 100;

                dto.setGrowthRate(
                        String.format("%.1f%% %s",
                                Math.abs(rate),
                                rate >= 0 ? "상승" : "하락")
                );
            } else {
                dto.setGrowthRate("-");
            }
        }

        return new PageImpl<>(content, pageable, page.getTotalElements());
    }
}
