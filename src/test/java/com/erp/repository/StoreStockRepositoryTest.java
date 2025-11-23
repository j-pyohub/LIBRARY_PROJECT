package com.erp.repository;

import com.erp.repository.entity.StoreStock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StoreStockRepositoryTest {

    @Autowired
    StoreStockRepository storeStockRepository;

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
