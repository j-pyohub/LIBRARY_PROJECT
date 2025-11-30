package com.erp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalesOrderDTO {

    private long salesOrderNo;
    private long storeNo;
    private LocalDateTime salesOrderDatetime;
    private String storeName;
    private int salesOrderCount;
    private int salesOrderAmount;

    public SalesOrderDTO(Long salesOrderNo,
                         Long storeNo,
                         LocalDateTime salesOrderDatetime,
                         String storeName,
                         Long salesOrderCount,
                         Long salesOrderAmount) {

        this.salesOrderNo = salesOrderNo;
        this.storeNo = storeNo;
        this.salesOrderDatetime = salesOrderDatetime;
        this.storeName = storeName;
        this.salesOrderCount = salesOrderCount.intValue();
        this.salesOrderAmount = salesOrderAmount.intValue();
    }

    public SalesOrderDTO(Long salesOrderNo, Long salesOrderCount, Long salesOrderAmount) {
        this.salesOrderNo = salesOrderNo;
        this.salesOrderCount = salesOrderCount.intValue();
        this.salesOrderAmount = salesOrderAmount.intValue();
    }
}
