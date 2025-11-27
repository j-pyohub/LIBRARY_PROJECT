package com.erp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalesOrderDetailDTO {
    private String menuName;
    private String size;
    private Integer price;
    private Integer count;
    private Integer totalPrice;
}
