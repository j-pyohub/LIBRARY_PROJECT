package com.erp.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 공통 페이지 응답 DTO
 * - content      : 현재 페이지 데이터 목록
 * - page/size    : 현재 페이지 번호(0-base), 페이지 크기
 * - total*       : 전체 건수, 전체 페이지 수
 * - start/endPage: 하단 페이징 블럭 시작/끝 (1-base)
 * - hasPrev/Next : « / » 블럭 버튼 여부
 */
@Getter
@Builder
public class PageResponseDTO<T> {

    /** 현재 페이지 데이터 목록 */
    private final List<T> content;

    /** 현재 페이지 번호 (0-base, Spring Page 번호와 동일) */
    private final int page;

    /** 페이지 크기 */
    private final int size;

    /** 전체 데이터 건수 */
    private final long totalElements;

    /** 전체 페이지 수 */
    private final int totalPages;

    /** 페이징 블럭 시작 페이지 (1-base) */
    private final int startPage;

    /** 페이징 블럭 끝 페이지 (1-base) */
    private final int endPage;

    /** 이전 블럭 존재 여부 (startPage > 1 인지 여부) */
    private final boolean hasPrevBlock;

    /** 다음 블럭 존재 여부 (endPage < totalPages 인지 여부) */
    private final boolean hasNextBlock;
}
