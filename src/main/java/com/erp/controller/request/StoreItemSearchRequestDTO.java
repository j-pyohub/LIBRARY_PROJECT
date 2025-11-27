package com.erp.controller.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 재고 조회 검색 조건 DTO (본사/직영점 공용)
 *
 * - 본사 화면:
 *   - storeNo: 드롭다운에서 선택한 직영점 번호
 *
 * - 직영점 화면:
 *   - storeNo: 화면에서 안 받고, 컨트롤러에서 로그인 정보로 세팅
 */
@Getter
@Setter
public class StoreItemSearchRequestDTO {

    /** 직영점 번호 (필수) */
    private Long storeNo;

    /** 카테고리: "전체" / "도우" / "소스" ... (null 또는 "전체"면 조건 무시) */
    private String category;

    /** 검색 타입: "NAME"(품목명), "CODE"(품목코드) */
    private String searchType;

    /** 검색어 (선택) */
    private String keyword;

    /** 페이지 번호 (0-base) */
    private Integer page;

    /** 페이지 크기 */
    private Integer size;
}
