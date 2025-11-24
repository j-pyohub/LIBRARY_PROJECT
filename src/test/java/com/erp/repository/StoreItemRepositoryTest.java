package com.erp.repository;

import com.erp.repository.dto.StoreItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StoreItemRepositoryTest {

    @Autowired
    StoreItemRepository storeItemRepository;

    @Test
    void findStoreItemsByStoreNo() {

        Long storeNo = 1L;

        List<StoreItemDTO> list =
                storeItemRepository.findStoreItemsByStoreNo(storeNo);

        System.out.println("=== 재고 현황 (storeNo=" + storeNo + ") ===");
        System.out.println("직영점 | 품목코드 | 품목명 | 카테고리 | 하한선 | 현재 재고 | 단위");

        list.forEach(row -> System.out.printf(
                "%s | %s | %s | %s | %d | %d | %s%n",
                row.getStoreName(),
                row.getItemCode(),
                row.getItemName(),
                row.getItemCategory(),
                row.getFinalLimit(),
                row.getCurrentQuantity(),
                row.getStockUnit()
        ));
        System.out.println("=========================================");

        // 기본 검증 (데이터 있으면)
        assertThat(list).isNotNull();
    }

    @Test
    void findStoreItemsByCategory() {
        List<StoreItemDTO> list =
                storeItemRepository.findStoreItemsByCategory(1L, "도우");

        System.out.println("=== 카테고리=도우 재고 현황 ===");
        list.forEach(System.out::println);

        assertThat(list).isNotNull();
    }

    @Test
    void findStoreItemsByItemName() {
        List<StoreItemDTO> list =
                storeItemRepository.findStoreItemsByItemName(1L, "치즈");

        System.out.println("=== 품목명 like '치즈' 재고 현황 ===");
        list.forEach(System.out::println);

        assertThat(list).isNotNull();
    }

    @Test
    void findStoreItemsByItemCode() {
        List<StoreItemDTO> list =
                storeItemRepository.findStoreItemsByItemCode(1L, "DOUGH-");

        System.out.println("=== 품목코드 like 'DOUGH-' 재고 현황 ===");
        list.forEach(System.out::println);

        assertThat(list).isNotNull();
    }
}
