package com.erp.service;

import com.erp.controller.exception.InvalidStoreItemLimitException;
import com.erp.controller.exception.StoreItemLimitConflictException;
import com.erp.controller.exception.StoreItemNotFoundException;
import com.erp.controller.exception.StoreNotSelectedException;
import com.erp.controller.request.StoreItemSearchRequestDTO;
import com.erp.dto.PageResponseDTO;
import com.erp.dto.StoreItemDTO;
import com.erp.repository.StoreItemRepository;
import com.erp.repository.entity.StoreItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class StoreItemService {

    private final StoreItemRepository storeItemRepository;

    /**
     * 재고 조회 (본사/직영점 공용)
     */
    @Transactional(readOnly = true)
    public PageResponseDTO<StoreItemDTO> getStoreItems(StoreItemSearchRequestDTO request) {

        // 직영점 번호는 필수
        if (request.getStoreNo() == null) {
            throw new StoreNotSelectedException();
        }

        // 1) 카테고리: "전체" 또는 빈값이면 null 처리 → 조건 무시
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
                searchType = searchType.toUpperCase(); // "NAME" / "CODE" 가 들어오도록 맞출 예정
            }
        }

        // 3) 페이지/사이즈 기본값 (0-base page)
        int page = (request.getPage() == null || request.getPage() < 0)
                ? 0
                : request.getPage();

        int size = (request.getSize() == null || request.getSize() <= 0)
                ? 10
                : request.getSize();

        // JPQL 안에 이미 ORDER BY가 있으므로, Pageable은 정렬 없이 넘김
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
        int blockSize = 10;                     // 한 번에 보여줄 페이지 번호 개수
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

    /**
     * 하한선 설정/변경/삭제
     *
     * @param storeItemNo  재고 품목 PK
     * @param newLimit     새 하한선 (null 이면 해당 역할의 하한선 제거)
     * @param isManagerRole true면 본사(ROLE_MANAGER/ADMIN), false면 직영점(ROLE_STORE)
     */
    @Transactional
    public void setStoreItemLimit(Long storeItemNo,
                                  Integer newLimit,
                                  boolean isManagerRole) {

        // 0, 음수는 허용 안 함 (null 은 '삭제' 의미로 허용)
        if (newLimit != null && newLimit <= 0) {
            throw new InvalidStoreItemLimitException(newLimit);
        }

        // 품목 조회
        StoreItem storeItem = storeItemRepository.findById(storeItemNo)
                .orElseThrow(() -> new StoreItemNotFoundException(storeItemNo));

        // 본사 하한선이 이미 있고, 직영점 계정이 수정하려고 하면 차단
        if (!isManagerRole && storeItem.getManagerLimit() != null) {
            throw new StoreItemLimitConflictException();
        }

        // 역할에 따라 하한선 필드 분기
        if (isManagerRole) {
            // ROLE_MANAGER / ROLE_ADMIN → 본사 하한선
            storeItem.setManagerLimit(newLimit);   // null 이면 본사 하한선 제거
        } else {
            // ROLE_STORE → 직영점 하한선
            storeItem.setStoreLimit(newLimit);     // null 이면 직영점 하한선 제거
        }

        // 변경 감지 + save 명시 호출 (둘 중 하나로도 동작 가능)
        storeItemRepository.save(storeItem);
    }
}
