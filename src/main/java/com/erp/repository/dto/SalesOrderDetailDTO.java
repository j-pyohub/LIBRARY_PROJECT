package com.erp.repository.dto;

import lombok.*;

import java.time.LocalDateTime;

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
