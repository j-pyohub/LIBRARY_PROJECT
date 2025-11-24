package com.erp.repository.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalesOrderDTO {
    private long salesOrderNo;
    private long storeNo;
    private String storeName;
    private int salesOrderCount;
    private int salesOrderAmount;


    public SalesOrderDTO(Long salesOrderNo, Long salesOrderCount, Long salesOrderAmount) {
        this.salesOrderNo = salesOrderNo;
        this.salesOrderCount = salesOrderCount.intValue();
        this.salesOrderAmount = salesOrderAmount.intValue();
    }
}
