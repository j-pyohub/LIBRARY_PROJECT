package com.erp.service;

import com.erp.controller.request.StoreItemSearchRequestDTO;
import com.erp.dto.PageResponseDTO;
import com.erp.repository.StoreItemRepository;
import com.erp.dto.StoreItemDTO;
import com.erp.repository.entity.StoreItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class StoreItemService {

    // @RequiredArgsConstructor 사용 → 의존성은 반드시 private final
    private final StoreItemRepository storeItemRepository;

    /**
     * 재고 조회 (본사/직영점 공용)
     *
     * - 처음 진입: storeNo 만 있고 나머지는 null → 전체 재고 1페이지
     * - 카테고리 선택: category만 채움
     * - 품목명/코드 검색: searchType + keyword 채움
     * - 초기화: storeNo 유지, 나머지 필드 null로 만들어서 다시 호출
     */
    @Transactional(readOnly = true)
    public PageResponseDTO<StoreItemDTO> getStoreItems(StoreItemSearchRequestDTO request) {

        if (request.getStoreNo() == null) {
            throw new IllegalArgumentException("직영점을 선택해야 합니다.");
        }

        // 1) 카테고리: "전체" 또는 빈값이면 null 처리
        String category = request.getCategory();
        if (!StringUtils.hasText(category) || "전체".equals(category)) {
            category = null;
        }

        // 2) 검색 타입 / 키워드 정리
        String searchType = request.getSearchType();
        String keyword = request.getKeyword();

        if (!StringUtils.hasText(keyword)) {
            keyword = null;
            searchType = null;
        } else {
            keyword = keyword.trim();
            if (!StringUtils.hasText(searchType)) {
                keyword = null;
                searchType = null;
            } else {
                searchType = searchType.toUpperCase(); // "NAME" / "CODE"
            }
        }

        // 3) 페이지/사이즈 기본값
        int page = (request.getPage() == null || request.getPage() < 0)
                ? 0
                : request.getPage();

        int size = (request.getSize() == null || request.getSize() <= 0)
                ? 10
                : request.getSize();

        Pageable pageable = PageRequest.of(page, size);

        // 4) 레포지토리 호출 (DTO 프로젝션 + Page)
        Page<StoreItemDTO> result = storeItemRepository.searchStoreItems(
                request.getStoreNo(),
                category,
                searchType,
                keyword,
                pageable
        );

        // 5) 블럭 페이지네이션 계산
        int totalPages = result.getTotalPages();
        int blockSize = 10;
        int currentPage = result.getNumber() + 1;  // 1-base

        int startPage;
        int endPage;
        boolean hasPrevBlock;
        boolean hasNextBlock;

        if (totalPages == 0) {
            startPage = 0;
            endPage = 0;
            hasPrevBlock = false;
            hasNextBlock = false;
        } else {
            int blockIndex = (currentPage - 1) / blockSize;
            startPage = blockIndex * blockSize + 1;
            endPage = Math.min(startPage + blockSize - 1, totalPages);

            hasPrevBlock = startPage > 1;
            hasNextBlock = endPage < totalPages;
        }

        // 6) Page → PageResponseDTO 로 포장
        return PageResponseDTO.<StoreItemDTO>builder()
                .content(result.getContent())
                .page(result.getNumber())          // 0-base
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(totalPages)
                .startPage(startPage)
                .endPage(endPage)
                .hasPrevBlock(hasPrevBlock)
                .hasNextBlock(hasNextBlock)
                .build();
    }

    @Transactional
    public void updateLowerLimit(Long storeItemNo, Integer newLimit, boolean managerSide) {

        StoreItem storeItem = storeItemRepository.findById(storeItemNo)
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 재고 품목입니다. storeItemNo=" + storeItemNo)
                );

        if (managerSide) {
            // 본사(ROLE_MANAGER / ROLE_ADMIN)가 설정하는 하한선
            storeItem.setManagerLimit(newLimit);
        } else {
            // 직영점(ROLE_STORE)이 설정하는 하한선
            storeItem.setStoreLimit(newLimit);
        }
        // @Transactional + JPA 더티체킹으로 자동 UPDATE
    }
}

