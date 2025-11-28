package com.erp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KpiDTO {
    private Integer totalSales;
    private Integer totalMenuCount;
    private Integer avgStoreSales;
    private Double salesGrowthRate;
}