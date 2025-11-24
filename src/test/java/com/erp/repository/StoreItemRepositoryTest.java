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
    void setStockLimits() {

        StoreItem item = storeItemRepository.findByStoreNoAndItemNo(1L, 1L)
                .get(0);

        System.out.println("변경 전: " + item);

        item.setManagerLimit(60);
        item.setStoreLimit(40);

        StoreItem updated = storeItemRepository.save(item);

        System.out.println("변경 후: " + updated);
    }


    @Test
    void findItemsByStoreNo() {
        List<StoreItem> list = storeItemRepository.findByStoreNo(1L);
        list.forEach(System.out::println);
    }

    @Test
    void findItemsByStoreNoAndItemNo() {
        List<StoreItem> list = storeItemRepository.findByStoreNoAndItemNo(1L, 1L);
        list.forEach(System.out::println);
    }

    @Test
    void findItemsByCategory() {
        List<StoreItem> list = storeItemRepository.findByCategory(1L, "도우");
        list.forEach(System.out::println);
    }

    @Test
    void findItemsByItemName() {
        List<StoreItem> list = storeItemRepository.findByItemName(1L, "치즈");
        list.forEach(System.out::println);
    }

    @Test
    void findItemsByItemCode() {
        List<StoreItem> list = storeItemRepository.findByItemCode(1L, "DOUGH-");
        list.forEach(System.out::println);
    }
}
