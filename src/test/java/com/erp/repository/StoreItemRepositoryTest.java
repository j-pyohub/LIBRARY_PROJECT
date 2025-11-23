package com.erp.repository;

import com.erp.repository.entity.StoreItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StoreItemRepositoryTest {

    @Autowired
    StoreItemRepository storeItemRepository;

    @Test
    void 하한선_수정() {

        StoreItem item = storeItemRepository.findByStoreNoAndItemNo(1L, 1L)
                .get(0);

        System.out.println("변경 전: " + item);

        item.setManagerLimit(60);
        item.setStoreLimit(40);

        StoreItem updated = storeItemRepository.save(item);

        System.out.println("변경 후: " + updated);
    }


    @Test
    void 매장번호로_조회() {
        List<StoreItem> list = storeItemRepository.findByStoreNo(1L);
        list.forEach(System.out::println);
    }

    @Test
    void 매장번호_품목번호로_조회() {
        List<StoreItem> list = storeItemRepository.findByStoreNoAndItemNo(1L, 1L);
        list.forEach(System.out::println);
    }

    @Test
    void 카테고리로_조회() {
        List<StoreItem> list = storeItemRepository.findByCategory(1L, "도우");
        list.forEach(System.out::println);
    }

    @Test
    void 품목명_조회() {
        List<StoreItem> list = storeItemRepository.findByItemName(1L, "치즈");
        list.forEach(System.out::println);
    }

    @Test
    void 품목코드_조회() {
        List<StoreItem> list = storeItemRepository.findByItemCode(1L, "DOUGH-");
        list.forEach(System.out::println);
    }
}
