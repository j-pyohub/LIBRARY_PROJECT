package com.erp.repository;

import com.erp.repository.dto.StoreItemDTO;
import com.erp.repository.entity.StoreItem;
import org.junit.jupiter.api.DisplayName;
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
        System.out.println("직영점 | 품목코드 | 품목명 | 카테고리 | 하한선 | 설정주체 | 현재 재고 | 단위");

        list.forEach(row -> {
            Integer finalLimit = row.getFinalLimit();
            String finalLimitStr = (finalLimit == null) ? "-" : finalLimit.toString();

            String ownerCode = row.getLimitOwner(); // MANAGER / STORE / NONE
            String ownerLabel =
                    "MANAGER".equals(ownerCode) ? "본사" :
                            "STORE".equals(ownerCode)   ? "매장" :
                                    "미설정";

            System.out.printf(
                    "%s | %s | %s | %s | %s | %s | %d | %s%n",
                    row.getStoreName(),
                    row.getItemCode(),
                    row.getItemName(),
                    row.getItemCategory(),
                    finalLimitStr,
                    ownerLabel,
                    row.getCurrentQuantity(),
                    row.getStockUnit()
            );
        });

        System.out.println("=========================================");

        assertThat(list).isNotNull();
    }

    @Test
    void setManagerLimitAndCheck() {

        // 테스트용 StoreItem 한 개 가져오기 (샘플 데이터 있다고 가정)
        StoreItem storeItem = storeItemRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("StoreItem 샘플 데이터가 필요합니다."));

        Long storeItemNo = storeItem.getStoreItemNo();
        Long storeNo = storeItem.getStoreNo();

        // 1) 본사 하한선만 설정 (managerLimit = 100, storeLimit = null)
        storeItem.setManagerLimit(100);
        storeItem.setStoreLimit(null);
        storeItemRepository.save(storeItem); // ★ 이게 UPDATE

        // 2) DTO로 다시 조회해서 값 확인
        StoreItemDTO dto = storeItemRepository.findStoreItemsByStoreNo(storeNo).stream()
                .filter(row -> row.getStoreItemNo().equals(storeItemNo))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("StoreItemDTO를 찾을 수 없습니다. (MANAGER)"));

        System.out.println("=== MANAGER 하한선 설정 결과 ===");
        System.out.println(dto);
        System.out.println("===============================");

        assertThat(dto.getFinalLimit()).isEqualTo(100);
        assertThat(dto.getLimitOwner()).isEqualTo("MANAGER");
    }

    @Test
    void setStoreLimitAndCheck() {

        // 테스트용 StoreItem 한 개 가져오기 (위 테스트와 같은 row 써도 됨)
        StoreItem storeItem = storeItemRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("StoreItem 샘플 데이터가 필요합니다."));

        Long storeItemNo = storeItem.getStoreItemNo();
        Long storeNo = storeItem.getStoreNo();

        // 1) 매장 하한선만 설정 (managerLimit = null, storeLimit = 50)
        storeItem.setManagerLimit(null);
        storeItem.setStoreLimit(50);
        storeItemRepository.save(storeItem);

        // 2) DTO로 다시 조회해서 값 확인
        StoreItemDTO dto = storeItemRepository.findStoreItemsByStoreNo(storeNo).stream()
                .filter(row -> row.getStoreItemNo().equals(storeItemNo))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("StoreItemDTO를 찾을 수 없습니다. (STORE)"));

        System.out.println("=== STORE 하한선 설정 결과 ===");
        System.out.println(dto);
        System.out.println("==============================");

        assertThat(dto.getFinalLimit()).isEqualTo(50);
        assertThat(dto.getLimitOwner()).isEqualTo("STORE");
    }

    @Test
    @DisplayName("카테고리 필터 조회")
    void findStoreItemsByCategory() {
        List<StoreItemDTO> list =
                storeItemRepository.findStoreItemsByCategory(1L, "도우");

        System.out.println("=== 카테고리=도우 재고 현황 ===");
        list.forEach(System.out::println);

        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("품목명 검색 조회")
    void findStoreItemsByItemName() {
        List<StoreItemDTO> list =
                storeItemRepository.findStoreItemsByItemName(1L, "치즈");

        System.out.println("=== 품목명 like '치즈' 재고 현황 ===");
        list.forEach(System.out::println);

        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("품목코드 검색 조회")
    void findStoreItemsByItemCode() {
        List<StoreItemDTO> list =
                storeItemRepository.findStoreItemsByItemCode(1L, "DOUGH-");

        System.out.println("=== 품목코드 like 'DOUGH-' 재고 현황 ===");
        list.forEach(System.out::println);

        assertThat(list).isNotNull();
    }
}
