package com.erp.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StoreSalesDTO {
    private long storeSalesNo;
    private long storeNo;
    private long orderCount;
    private String address;
    private String storeName;
    private String salesDate;
    private int salesPrice;
}
