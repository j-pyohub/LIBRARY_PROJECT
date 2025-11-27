package com.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 재고 조회(직영점) 테이블 한 행(row)용 DTO
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

    /**
     * 최종 하한선 값
     * - managerLimit / storeLimit 중 하나 (COALESCE)
     */
    private Integer finalLimit;

    /**
     * 하한선을 누가 설정했는지
     * - "MANAGER" : 본사(ROLE_ADMIN, ROLE_MANAGER 계열)
     * - "STORE"   : 직영점(ROLE_STORE)
     * - "NONE"    : 미설정
     */
    private String limitOwner;

    /** 현재 재고 수량 (STORE_STOCK 최신 로그의 currentQuantity) */
    private Integer currentQuantity;

    /** 재고 단위 (ea, g 등) */
    private String stockUnit;
}
