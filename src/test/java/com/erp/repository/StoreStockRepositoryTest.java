package com.erp.repository;

import com.erp.repository.entity.StoreStock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class StoreStockRepositoryTest {

    @Autowired
    StoreStockRepository storeStockRepository;

    // 최신 재고 조회 함수 (repository 직접 호출)
    private int getLatestQuantity(Long storeItemNo) {
        StoreStock log = storeStockRepository
                .findFirstByStoreItemNoOrderByStoreStockNoDesc(storeItemNo);

        return log == null ? 0 : log.getCurrentQuantity();
    }


    @Test
    void 현재_재고조회() {

        int latest = getLatestQuantity(1L);

        System.out.println("=== 현재 재고 ===");
        System.out.println("latestQuantity = " + latest);
        System.out.println("================");

        assertThat(latest).isGreaterThanOrEqualTo(0);
    }

    @Test
    void 입고기록_추가() {

        int latest = getLatestQuantity(1L);

        StoreStock newLog = StoreStock.builder()
                .storeItemNo(1L)
                .changeQuantity(500)
                .changeReason("입고")
                .currentQuantity(latest + 500)
                .build();

        StoreStock saved = storeStockRepository.save(newLog);

        System.out.println("=== 입고 기록 추가 ===");
        System.out.println(saved);
        System.out.println("=====================");

        assertThat(saved.getCurrentQuantity()).isEqualTo(latest + 500);
    }

    @Test
    void 판매기록_추가() {

        int latest = getLatestQuantity(1L);

        StoreStock newLog = StoreStock.builder()
                .storeItemNo(1L)
                .changeQuantity(-200)
                .changeReason("판매")
                .currentQuantity(latest - 200)
                .build();

        StoreStock saved = storeStockRepository.save(newLog);

        System.out.println("=== 판매 기록 추가 ===");
        System.out.println(saved);
        System.out.println("=====================");

        assertThat(saved.getCurrentQuantity()).isEqualTo(latest - 200);
    }

    @Test
    void 폐기기록_폐기사유_추가() {

        int latest = getLatestQuantity(1L);
        int 폐기수량 = 2;

        StoreStock newLog = StoreStock.builder()
                .storeItemNo(1L)
                .changeQuantity(-폐기수량)
                .changeReason("폐기")
                .currentQuantity(latest - 폐기수량)
                .disposalReason("유통기한 경과")
                .build();

        StoreStock saved = storeStockRepository.save(newLog);

        System.out.println("=== 폐기 기록 추가 ===");
        System.out.println(saved);
        System.out.println("======================");

        assertThat(saved.getDisposalReason()).isEqualTo("유통기한 경과");
        assertThat(saved.getCurrentQuantity()).isEqualTo(latest - 폐기수량);
    }

    @Test
    void 직영점_전체_재고변동조회() {

        Long storeNo = 1L;
        List<StoreStock> result = storeStockRepository.findAllByStoreNo(storeNo);

        System.out.println("=== 전체 변동 정보 ===");
        result.forEach(System.out::println);
        System.out.println("=====================");
    }

    @Test
    void 재고변동_품목명_검색() {

        Long storeNo = 1L;
        String itemName = "도우";

        List<StoreStock> result =
                storeStockRepository.searchByItemName(storeNo, itemName);

        System.out.println("=== 품목명 검색 결과 ===");
        result.forEach(System.out::println);
        System.out.println("======================");
    }

    @Test
    void 재고변동_품목코드_검색() {

        Long storeNo = 1L;
        String itemCode = "A-01";

        List<StoreStock> result =
                storeStockRepository.searchByItemCode(storeNo, itemCode);

        System.out.println("=== 품목코드 검색 결과 ===");
        result.forEach(System.out::println);
        System.out.println("=========================");
    }
}
