package com.erp.controller.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StoreItemSearchRequestDTO {

    private Long storeNo;
    private String category;
    private String searchType;
    private String keyword;

    private Integer page;
    private Integer size;
}
