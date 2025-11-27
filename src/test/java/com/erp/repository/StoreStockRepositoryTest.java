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


    private StoreStock dispose(Long storeItemNo, int quantity, String reason) {

        int latest = getLatestQuantity(storeItemNo);

        StoreStock newLog = StoreStock.builder()
                .storeItemNo(storeItemNo)
                .changeQuantity(-quantity)         // -수량
                .changeReason("폐기")
                .currentQuantity(latest - quantity)
                .disposalReason(reason)
                .build();

        return storeStockRepository.save(newLog);   // INSERT
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

        Long storeItemNo = 1L;
        int latest = getLatestQuantity(storeItemNo);
        int 폐기수량 = 2;
        String reason = "유통기한 경과";

        // ★ 나중에 Service에서 쓸 메서드 이름/형태로 호출
        StoreStock saved = dispose(storeItemNo, 폐기수량, reason);

        System.out.println("=== 폐기 기록 추가 ===");
        System.out.println(saved);
        System.out.println("======================");

        assertThat(saved.getDisposalReason()).isEqualTo(reason);
        assertThat(saved.getCurrentQuantity()).isEqualTo(latest - 폐기수량);
        assertThat(saved.getChangeReason()).isEqualTo("폐기");
        assertThat(saved.getChangeQuantity()).isEqualTo(-폐기수량);
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
}
