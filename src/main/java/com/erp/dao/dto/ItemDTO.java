package com.erp.dao.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ItemDTO {

    private Long itemNo;
    private String itemCode;
    private String itemCategory;
    private String itemName;
    private String ingredientName;
    private String stockUnit;
    private String supplyUnit;
    private String supplier;
    private Integer itemPrice;
    private Integer convertStock;
    private String storageType;
    private String expirationType;
    private Integer expiration;
    private String itemImage;
    private String note;

    private Timestamp inDate;
    private Timestamp editDate;
    private Timestamp delDate;
}
