package com.erp.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StoreDTO {
    private long storeNo;
    private String address;
    private String managerName;
    private String storeName;
    private String storeStatus;
}
