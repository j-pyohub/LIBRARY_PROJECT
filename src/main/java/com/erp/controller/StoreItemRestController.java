package com.erp.controller;

import com.erp.controller.request.StoreItemSearchRequestDTO;
import com.erp.dto.PageResponseDTO;
import com.erp.dto.StoreItemDTO;
import com.erp.service.StoreItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StoreItemRestController {

    private final StoreItemService storeItemService;

    /**
     * 재고 조회 API (본사/직영점 공용)
     *
     * 예시:
     * GET /api/stock/items?storeNo=1&page=0&size=10&category=도우&searchType=NAME&keyword=도우
     */
    @GetMapping("/items")
    public ResponseEntity<PageResponseDTO<StoreItemDTO>> getStoreItems(
            StoreItemSearchRequestDTO request
    ) {
        PageResponseDTO<StoreItemDTO> result = storeItemService.getStoreItems(request);
        return ResponseEntity.ok(result);
    }

    /**
     * 하한선 설정/변경/삭제 API
     *
     * - storeItemNo : path variable
     * - newLimit    : null 이면 하한선 삭제
     * - managerRole : true → 본사, false → 직영점
     *
     * 예시:
     * POST /api/stock/items/1/limit?newLimit=50&managerRole=true
     * POST /api/stock/items/1/limit?managerRole=false      (직영점 하한선 삭제)
     */
    @PostMapping("/items/{storeItemNo}/limit")
    public ResponseEntity<Void> setStoreItemLimit(
            @PathVariable Long storeItemNo,
            @RequestParam(required = false) Integer newLimit,
            @RequestParam boolean managerRole
    ) {
        storeItemService.setStoreItemLimit(storeItemNo, newLimit, managerRole);
        return ResponseEntity.noContent().build();   // 204
    }
}
