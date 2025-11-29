package com.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesListDTO {

    private Long storeNo;
    private String storeName;
    private String address;
    private int orderCount;
    private int salesAmount;
    private LocalDate salesDate;
    private String growthRate;

}
