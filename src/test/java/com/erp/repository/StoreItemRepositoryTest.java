package com.erp.repository;

import com.erp.dto.StoreItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StoreItemRepositoryTest {

    @Autowired
    StoreItemRepository storeItemRepository;

    /**
     * 1) 직영점 전체 재고 조회 (검색조건 없음)
     */
    @Test
    void searchStoreItems_all() {

        Long storeNo = 1L;

        PageRequest pageable = PageRequest.of(0, 10);

        Page<StoreItemDTO> page =
                storeItemRepository.searchStoreItems(
                        storeNo,
                        null,   // category
                        null,   // searchType
                        null,   // keyword
                        pageable
                );

        System.out.println("=== [Repo] 전체 재고 (storeNo=" + storeNo + ") 1페이지 ===");
        page.getContent().forEach(row -> System.out.printf(
                "%s | %s | %s | %s | finalLimit=%s | current=%s %s | owner=%s%n",
                row.getStoreName(),
                row.getItemCode(),
                row.getItemName(),
                row.getItemCategory(),
                row.getFinalLimit(),
                row.getCurrentQuantity(),
                row.getStockUnit(),
                row.getLimitOwner()
        ));
        System.out.println("totalElements = " + page.getTotalElements());
        System.out.println("totalPages    = " + page.getTotalPages());
        System.out.println("==========================================");

        assertThat(page).isNotNull();
    }

    /**
     * 2) 카테고리 필터 테스트
     */
    @Test
    void searchStoreItems_byCategory() {

        Long storeNo = 1L;
        String category = "도우";

        Page<StoreItemDTO> page =
                storeItemRepository.searchStoreItems(
                        storeNo,
                        category,
                        null,
                        null,
                        PageRequest.of(0, 10)
                );

        System.out.println("=== [Repo] 카테고리=" + category + " 재고 ===");
        page.getContent().forEach(System.out::println);
        System.out.println("==========================================");

        assertThat(page).isNotNull();
    }

    /**
     * 3) 품목명 검색 테스트
     */
    @Test
    void searchStoreItems_byItemName() {

        Long storeNo = 1L;

        Page<StoreItemDTO> page =
                storeItemRepository.searchStoreItems(
                        storeNo,
                        null,
                        "NAME",     // 품목명 검색
                        "치즈",      // 예시 검색어
                        PageRequest.of(0, 10)
                );

        System.out.println("=== [Repo] 품목명 like '치즈' 재고 ===");
        page.getContent().forEach(System.out::println);
        System.out.println("====================================");

        assertThat(page).isNotNull();
    }

    /**
     * 4) 품목코드 검색 테스트
     */
    @Test
    void searchStoreItems_byItemCode() {

        Long storeNo = 1L;

        Page<StoreItemDTO> page =
                storeItemRepository.searchStoreItems(
                        storeNo,
                        null,
                        "CODE",     // 품목코드 검색
                        "DOUGH-",   // 예시 검색어
                        PageRequest.of(0, 10)
                );

        System.out.println("=== [Repo] 품목코드 like 'DOUGH-' 재고 ===");
        page.getContent().forEach(System.out::println);
        System.out.println("=======================================");

        assertThat(page).isNotNull();
    }
}
