package com.erp.service;

import com.erp.controller.exception.InvalidStoreItemLimitException;
import com.erp.controller.exception.StoreItemLimitConflictException;
import com.erp.controller.exception.StoreItemNotFoundException;
import com.erp.controller.exception.StoreNotSelectedException;
import com.erp.controller.request.StoreItemSearchRequestDTO;
import com.erp.dto.PageResponseDTO;
import com.erp.dto.StoreItemDTO;
import com.erp.repository.StoreItemRepository;
import com.erp.repository.entity.StoreItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class StoreItemServiceTest {

    // 스프링 빈이 아니라, 테스트 안에서 직접 만든 목 객체
    private StoreItemRepository storeItemRepository;
    private StoreItemService storeItemService;

    @BeforeEach
    void setUp() {
        // @MockBean 대신 직접 목 생성
        storeItemRepository = mock(StoreItemRepository.class);
        storeItemService = new StoreItemService(storeItemRepository);
    }

    // ==========================
    // 1) 재고 조회 기본 테스트
    // ==========================
    @Test
    void getStoreItems_basic() {

        // given
        StoreItemSearchRequestDTO req = new StoreItemSearchRequestDTO();
        req.setStoreNo(1L);
        req.setPage(0);
        req.setSize(10);

        StoreItemDTO row = StoreItemDTO.builder()
                .storeItemNo(1L)
                .storeNo(1L)
                .storeName("1호점")
                .itemNo(1L)
                .itemCode("DOUGH-01")
                .itemName("도우 1번")
                .itemCategory("도우")
                .finalLimit(50)
                .limitOwner("MANAGER")
                .currentQuantity(120)
                .stockUnit("EA")
                .build();

        Page<StoreItemDTO> pageResult = new PageImpl<>(
                List.of(row),
                PageRequest.of(0, 10),
                1
        );

        given(storeItemRepository.searchStoreItems(
                eq(1L),
                isNull(),      // category
                isNull(),      // searchType
                isNull(),      // keyword
                any(Pageable.class)
        )).willReturn(pageResult);

        // when
        PageResponseDTO<StoreItemDTO> response = storeItemService.getStoreItems(req);

        // then (콘솔로도 확인)
        System.out.println("=== getStoreItems_basic ===");
        System.out.println("총 건수 = " + response.getTotalElements());
        response.getContent().forEach(item -> System.out.println("row = " + item));
        System.out.println("===========================");

        assertThat(response.getContent()).hasSize(1);
        assertThat(response.getTotalElements()).isEqualTo(1);
        assertThat(response.getPage()).isEqualTo(0);
        assertThat(response.getTotalPages()).isEqualTo(1);
    }

    @Test
    void getStoreItems_shouldThrow_whenStoreNoIsNull() {
        // given
        StoreItemSearchRequestDTO req = new StoreItemSearchRequestDTO();
        // storeNo 안 넣음

        // when
        StoreNotSelectedException ex = assertThrows(
                StoreNotSelectedException.class,
                () -> storeItemService.getStoreItems(req)
        );

        // then
        System.out.println("[getStoreItems_shouldThrow_whenStoreNoIsNull] 메시지 = " + ex.getMessage());
        assertThat(ex.getMessage()).contains("직영점을 선택해야 합니다.");
    }

    // ======================================
    // 2) 하한선 설정 관련 테스트
    // ======================================

    @Test
    void setStoreItemLimit_byManager_success() {
        // given
        StoreItem item = StoreItem.builder()
                .storeItemNo(1L)
                .managerLimit(null)
                .storeLimit(null)
                .build();

        given(storeItemRepository.findById(1L))
                .willReturn(Optional.of(item));

        // when
        storeItemService.setStoreItemLimit(1L, 50, true);

        // then
        System.out.println("[setStoreItemLimit_byManager_success] managerLimit = " + item.getManagerLimit());
        assertThat(item.getManagerLimit()).isEqualTo(50);
        assertThat(item.getStoreLimit()).isNull();

        verify(storeItemRepository).save(item);
    }

    @Test
    void setStoreItemLimit_byStore_success_whenNoManagerLimit() {
        // given
        StoreItem item = StoreItem.builder()
                .storeItemNo(2L)
                .managerLimit(null)
                .storeLimit(null)
                .build();

        given(storeItemRepository.findById(2L))
                .willReturn(Optional.of(item));

        // when
        storeItemService.setStoreItemLimit(2L, 30, false);

        // then
        System.out.println("[setStoreItemLimit_byStore_success] storeLimit = " + item.getStoreLimit());
        assertThat(item.getManagerLimit()).isNull();
        assertThat(item.getStoreLimit()).isEqualTo(30);

        verify(storeItemRepository).save(item);
    }

    @Test
    void setStoreItemLimit_shouldThrow_whenManagerLimitExistsAndStoreTryToChange() {
        // given
        StoreItem item = StoreItem.builder()
                .storeItemNo(3L)
                .managerLimit(100)   // 이미 본사 하한선 존재
                .storeLimit(null)
                .build();

        given(storeItemRepository.findById(3L))
                .willReturn(Optional.of(item));

        // when
        StoreItemLimitConflictException ex = assertThrows(
                StoreItemLimitConflictException.class,
                () -> storeItemService.setStoreItemLimit(3L, 20, false)
        );

        // then
        System.out.println("[setStoreItemLimit_shouldThrow_whenManagerLimitExistsAndStoreTryToChange] 메시지 = " + ex.getMessage());
        assertThat(ex.getMessage()).contains("본사에서 이미 하한선을 설정한 품목입니다");

        assertThat(item.getStoreLimit()).isNull();
        assertThat(item.getManagerLimit()).isEqualTo(100);

        verify(storeItemRepository, never()).save(any());
    }

    @Test
    void setStoreItemLimit_shouldThrow_whenStoreItemNotFound() {
        // given
        given(storeItemRepository.findById(999L))
                .willReturn(Optional.empty());

        // when
        StoreItemNotFoundException ex = assertThrows(
                StoreItemNotFoundException.class,
                () -> storeItemService.setStoreItemLimit(999L, 10, true)
        );

        // then
        System.out.println("[setStoreItemLimit_shouldThrow_whenStoreItemNotFound] 메시지 = " + ex.getMessage());
        assertThat(ex.getMessage()).contains("존재하지 않는 재고 품목");

        verify(storeItemRepository, never()).save(any());
    }

    @Test
    void setStoreItemLimit_shouldThrowInvalid_whenLimitIsZeroOrNegative() {
        // 0, 음수 → Repository 에는 아예 접근하지 않아야 함

        InvalidStoreItemLimitException ex1 = assertThrows(
                InvalidStoreItemLimitException.class,
                () -> storeItemService.setStoreItemLimit(1L, 0, true)
        );
        System.out.println("[setStoreItemLimit_invalid_zero] 메시지 = " + ex1.getMessage());

        InvalidStoreItemLimitException ex2 = assertThrows(
                InvalidStoreItemLimitException.class,
                () -> storeItemService.setStoreItemLimit(1L, -10, false)
        );
        System.out.println("[setStoreItemLimit_invalid_negative] 메시지 = " + ex2.getMessage());

        // newLimit 체크에서 걸려서, findById / save 가 호출되면 안 됨
        verify(storeItemRepository, never()).findById(anyLong());
        verify(storeItemRepository, never()).save(any());
    }

    @Test
    void managerCanClearManagerLimit_withNull() {
        // given
        StoreItem item = StoreItem.builder()
                .storeItemNo(4L)
                .managerLimit(80)
                .storeLimit(50)
                .build();

        given(storeItemRepository.findById(4L))
                .willReturn(Optional.of(item));

        // when
        storeItemService.setStoreItemLimit(4L, null, true);

        // then
        System.out.println("[managerCanClearManagerLimit_withNull] managerLimit = " + item.getManagerLimit());
        assertThat(item.getManagerLimit()).isNull();
        assertThat(item.getStoreLimit()).isEqualTo(50);

        verify(storeItemRepository).save(item);
    }

    @Test
    void storeCanClearStoreLimit_withNull_whenNoManagerLimit() {
        // given
        StoreItem item = StoreItem.builder()
                .storeItemNo(5L)
                .managerLimit(null)
                .storeLimit(30)
                .build();

        given(storeItemRepository.findById(5L))
                .willReturn(Optional.of(item));

        // when
        storeItemService.setStoreItemLimit(5L, null, false);

        // then
        System.out.println("[storeCanClearStoreLimit_withNull_whenNoManagerLimit] storeLimit = " + item.getStoreLimit());
        assertThat(item.getManagerLimit()).isNull();
        assertThat(item.getStoreLimit()).isNull();

        verify(storeItemRepository).save(item);
    }
    /**
     * 카테고리 + 품목명으로 검색하는 경우
     *  - category = "도우"
     *  - searchType = "NAME"
     *  - keyword   = "도우"
     */
    @Test
    void getStoreItems_withCategoryAndItemName() {

        // given
        StoreItemSearchRequestDTO req = new StoreItemSearchRequestDTO();
        req.setStoreNo(1L);
        req.setCategory("도우");
        req.setSearchType("NAME");   // 서비스 안에서 toUpperCase() 한 번 더 타지만 이미 대문자
        req.setKeyword("도우");
        req.setPage(0);
        req.setSize(10);

        StoreItemDTO row = StoreItemDTO.builder()
                .storeItemNo(101L)
                .storeNo(1L)
                .storeName("1호점")
                .itemNo(10L)
                .itemCode("DOUGH-01")
                .itemName("도우 M 230g(레귤러)")
                .itemCategory("도우")
                .finalLimit(40)
                .limitOwner("MANAGER")
                .currentQuantity(26)
                .stockUnit("EA")
                .build();

        Page<StoreItemDTO> pageResult = new PageImpl<>(
                List.of(row),
                PageRequest.of(0, 10),
                1
        );

        // ★ category, searchType, keyword 까지 정확히 들어오는지 확인하려고 eq(...) 사용
        given(storeItemRepository.searchStoreItems(
                eq(1L),
                eq("도우"),     // category
                eq("NAME"),    // searchType
                eq("도우"),     // keyword
                any(Pageable.class)
        )).willReturn(pageResult);

        // when
        PageResponseDTO<StoreItemDTO> response = storeItemService.getStoreItems(req);

        // then
        System.out.println("=== [카테고리+품목명] 검색 결과 ===");
        response.getContent().forEach(item -> System.out.printf(
                "store=%s | code=%s | name=%s | category=%s | limit=%d | current=%d %s%n",
                item.getStoreName(),
                item.getItemCode(),
                item.getItemName(),
                item.getItemCategory(),
                item.getFinalLimit(),
                item.getCurrentQuantity(),
                item.getStockUnit()
        ));

        assertThat(response.getContent()).hasSize(1);
        assertThat(response.getPage()).isEqualTo(0);
        assertThat(response.getTotalPages()).isEqualTo(1);

        // 호출 파라미터가 우리가 기대한대로 들어갔는지 한 번 더 검증 (선택)
        verify(storeItemRepository, times(1)).searchStoreItems(
                eq(1L),
                eq("도우"),
                eq("NAME"),
                eq("도우"),
                any(Pageable.class)
        );
    }

    @Test
    void getStoreItems_withCategoryAndItemCode() {

        // given
        StoreItemSearchRequestDTO req = new StoreItemSearchRequestDTO();
        req.setStoreNo(1L);
        req.setCategory("도우");
        req.setSearchType("CODE");     // 품목코드 검색
        req.setKeyword("DOUGH");
        req.setPage(0);
        req.setSize(10);

        StoreItemDTO row = StoreItemDTO.builder()
                .storeItemNo(102L)
                .storeNo(1L)
                .storeName("1호점")
                .itemNo(11L)
                .itemCode("DOUGH-M-230G")
                .itemName("도우 M 230g(레귤러)")
                .itemCategory("도우")
                .finalLimit(40)
                .limitOwner("MANAGER")
                .currentQuantity(55)
                .stockUnit("EA")
                .build();

        Page<StoreItemDTO> pageResult = new PageImpl<>(
                List.of(row),
                PageRequest.of(0, 10),
                1
        );

        given(storeItemRepository.searchStoreItems(
                eq(1L),
                eq("도우"),       // category
                eq("CODE"),      // searchType
                eq("DOUGH"),     // keyword
                any(Pageable.class)
        )).willReturn(pageResult);

        // when
        PageResponseDTO<StoreItemDTO> response = storeItemService.getStoreItems(req);

        // then
        System.out.println("=== [카테고리+품목코드] 검색 결과 ===");
        response.getContent().forEach(item -> System.out.printf(
                "store=%s | code=%s | name=%s | category=%s | limit=%d | current=%d %s%n",
                item.getStoreName(),
                item.getItemCode(),
                item.getItemName(),
                item.getItemCategory(),
                item.getFinalLimit(),
                item.getCurrentQuantity(),
                item.getStockUnit()
        ));

        assertThat(response.getContent()).hasSize(1);
        assertThat(response.getPage()).isEqualTo(0);
        assertThat(response.getTotalPages()).isEqualTo(1);

        verify(storeItemRepository, times(1)).searchStoreItems(
                eq(1L),
                eq("도우"),
                eq("CODE"),
                eq("DOUGH"),
                any(Pageable.class)
        );
    }

}
