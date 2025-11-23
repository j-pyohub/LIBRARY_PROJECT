package com.erp.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreSellingMenuDTO {
    private String storeName;
    private String menuCode;
    private String menuName;
    private String size;
    private String menuPrice;
    private String salesStatus;
}