package com.erp.dto;

import lombok.Data;

@Data
public class MenuIngredientDTO {
    private Long itemNo;
    private Long menuNo;
    private String ingredientName;
    private String stockUnit;
    private Integer quantity;

    private Integer quantityLarge;       // 라지 정량
    private Integer quantityMedium;       // 미디움 정량
}
