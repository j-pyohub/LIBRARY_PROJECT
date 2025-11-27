package com.erp.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 재고 현황 페이지 한 줄(row)용 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StoreItemDTO {

    private Long storeItemNo;

    private Long storeNo;
    private String storeName;

    private Long itemNo;
    private String itemCode;
    private String itemName;
    private String itemCategory;

    // 하한선
    private Integer finalLimit;
    private String limitOwner;

    // 현재 재고 (STORE_STOCK 최신 로그의 current_quantity)
    private Integer currentQuantity;

    // 단위 (ea, g ...)
    private String stockUnit;
}
