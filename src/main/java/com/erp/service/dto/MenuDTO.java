package com.erp.service.dto;

import com.erp.repository.dto.MenuIngredientDTO;
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
