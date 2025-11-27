 package com.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StoreDetailDTO {
    private long storeNo;
    private String storeName;
    private String storeStatus;
    private String managerName;
    private String phoneNumber;
    private String address;
    private String storePhoneNumber;
    private String openedDate;
    private String closedDate;
    private String openTime;
    private String closeTime;
}
