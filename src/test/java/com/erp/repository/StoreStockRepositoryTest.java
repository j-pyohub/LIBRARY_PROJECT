package com.erp.repository;

import com.erp.repository.entity.StoreStock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
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
    void getlatestQuantity() {

        int latest = getLatestQuantity(1L);

        System.out.println("=== 현재 재고 ===");
        System.out.println("latestQuantity = " + latest);
        System.out.println("================");

        assertThat(latest).isGreaterThanOrEqualTo(0);
    }

    @Test
    void addInboundStockLog() {

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
    void addSalesStockLog() {

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
    void addDisposalStockLogWithReason() {

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
    void getAllStockChangesByStore() {

        Long storeNo = 1L;
        List<StoreStock> result = storeStockRepository.findAllByStoreNo(storeNo);

        System.out.println("=== 전체 변동 정보 ===");
        result.forEach(System.out::println);
        System.out.println("=====================");
    }

    @Test
    void searchStockChangesByItemName() {

        Long storeNo = 1L;
        String itemName = "도우";

        List<StoreStock> result =
                storeStockRepository.findByItemName(storeNo, itemName);

        System.out.println("=== 품목명 검색 결과 ===");
        result.forEach(System.out::println);
        System.out.println("======================");
    }

    @Test
    void searchStockChangesByItemCode() {

        Long storeNo = 1L;
        String itemCode = "A-01";

        List<StoreStock> result =
                storeStockRepository.findByItemCode(storeNo, itemCode);

        System.out.println("=== 품목코드 검색 결과 ===");
        result.forEach(System.out::println);
        System.out.println("=========================");
    }

    @Test
    void findByStoreNoAndChangeDatetimeBetween() {

        Long storeNo = 1L;
        Timestamp start = Timestamp.valueOf("2025-11-01 00:00:00");
        Timestamp end   = Timestamp.valueOf("2025-11-30 23:59:59");

        List<StoreStock> result =
                storeStockRepository.findByStoreNoAndChangeDatetimeBetween(storeNo, start, end);

        System.out.println("=== 기간별 검색 결과 ===");
        result.forEach(System.out::println);
        System.out.println("=======================");

        assertThat(result.size()).isGreaterThanOrEqualTo(0);
    }

}
