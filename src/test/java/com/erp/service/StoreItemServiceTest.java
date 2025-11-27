package com.erp.service;

import com.erp.controller.request.StoreItemSearchRequestDTO;
import com.erp.dto.PageResponseDTO;
import com.erp.dto.StoreItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StoreItemServiceTest {

    @Autowired
    StoreItemService storeItemService;

    /**
     * 1) 기본 조회 (직영점 + 페이지 정보만 세팅, 나머지는 null)
     */
    @Test
    void getStoreItems() {

        StoreItemSearchRequestDTO req = new StoreItemSearchRequestDTO();
        req.setStoreNo(1L);
        req.setPage(0);
        req.setSize(10);

        PageResponseDTO<StoreItemDTO> res =
                storeItemService.getStoreItems(req);

        System.out.println("=== [Service] 기본 조회 (storeNo=1) ===");
        res.getContent().forEach(row -> System.out.printf(
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
        System.out.println("page         = " + res.getPage());
        System.out.println("size         = " + res.getSize());
        System.out.println("totalElements= " + res.getTotalElements());
        System.out.println("totalPages   = " + res.getTotalPages());
        System.out.println("startPage    = " + res.getStartPage());
        System.out.println("endPage      = " + res.getEndPage());
        System.out.println("hasPrevBlock = " + res.isHasPrevBlock());
        System.out.println("hasNextBlock = " + res.isHasNextBlock());
        System.out.println("=======================================");

        assertThat(res).isNotNull();
    }

    /**
     * 2) 카테고리 필터 + 페이징
     */
    @Test
    void getStoreItemswithCategory() {

        StoreItemSearchRequestDTO req = new StoreItemSearchRequestDTO();
        req.setStoreNo(1L);
        req.setCategory("도우");
        req.setPage(0);
        req.setSize(10);

        PageResponseDTO<StoreItemDTO> res =
                storeItemService.getStoreItems(req);

        System.out.println("=== [Service] 카테고리=도우 조회 ===");
        res.getContent().forEach(System.out::println);
        System.out.println("===================================");

        assertThat(res).isNotNull();
    }

    /**
     * 3) 품목명 검색 + 페이징
     */
    @Test
    void getStoreItemswithItemName() {

        StoreItemSearchRequestDTO req = new StoreItemSearchRequestDTO();
        req.setStoreNo(1L);
        req.setSearchType("NAME");
        req.setKeyword("치즈");
        req.setPage(0);
        req.setSize(10);

        PageResponseDTO<StoreItemDTO> res =
                storeItemService.getStoreItems(req);

        System.out.println("=== [Service] 품목명 like '치즈' 조회 ===");
        res.getContent().forEach(System.out::println);
        System.out.println("======================================");

        assertThat(res).isNotNull();
    }

    /**
     * 4) 품목코드 검색 + 페이징
     */
    @Test
    void getStoreItemswithItemCode() {

        StoreItemSearchRequestDTO req = new StoreItemSearchRequestDTO();
        req.setStoreNo(1L);
        req.setSearchType("CODE");
        req.setKeyword("DOUGH-");
        req.setPage(0);
        req.setSize(10);

        PageResponseDTO<StoreItemDTO> res =
                storeItemService.getStoreItems(req);

        System.out.println("=== [Service] 품목코드 like 'DOUGH-' 조회 ===");
        res.getContent().forEach(System.out::println);
        System.out.println("==========================================");

        assertThat(res).isNotNull();
    }
}
