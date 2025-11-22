package com.erp.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MenuDTO {
    private Long menuNo;
    private String menuName;
    private String menuCode;
    private String menuCategory;
    private String menuExplain;
    private String size;
    private String menuImage;
    private String menuPrice;
    private String releaseStatus;
    private Timestamp inDate;
    private Timestamp editDate;
    private Timestamp delDate;
}
