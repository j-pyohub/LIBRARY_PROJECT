package com.erp.repository.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MenuDTO {
    private Long menuNo;
    private String menuName;
    private String menuCode;
    private String menuCategory;
    private String releaseStatus;
    private String size;
    private String menuExplain;
    private String menuImage;
    private Integer menuPrice;

    private List<MenuIngredientDTO> ingredients;
}
