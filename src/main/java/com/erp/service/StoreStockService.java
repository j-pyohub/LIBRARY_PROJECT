package com.erp.service;

import com.erp.repository.StoreStockRepository;
import com.erp.repository.entity.StoreStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreStockService {

    private final StoreStockRepository storeStockRepository;

    /**
     * 예시 )
     * 판매서비스에서 호출 : storeStockService.sell(storeItemNo, 주문수량);
     * 입고서비스에서 호출 : storeStockService.inbound(storeItemNo, 입고수량);
     * 폐기서비스에서 호출 : storeStockService.dispose(storeItemNo, 폐기수량, "유통기한 경과");
     * changeQty     변동 수량 (+입고, -판매/-폐기)
     * reason        "입고" / "판매" / "폐기"
     * disposalReason 폐기 사유(입고/판매면 null)
     */
    @Transactional
    public StoreStock recordStockChange(Long storeItemNo,
                                        int changeQty,
                                        String reason,
                                        String disposalReason) {

        // 1) 최신 로그에서 현재 재고 가져오기
        StoreStock latest = storeStockRepository
                .findFirstByStoreItemNoOrderByStoreStockNoDesc(storeItemNo);

        int latestQty = (latest != null) ? latest.getCurrentQuantity() : 0;

        // 2) 새 재고 계산
        int newCurrentQty = latestQty + changeQty;
        if (newCurrentQty < 0) {
            // 필요하면 예외 던져서 “재고 부족” 처리
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        // 3) 새 로그 생성
        StoreStock newLog = StoreStock.builder()
                .storeItemNo(storeItemNo)
                .changeQuantity(changeQty)
                .changeReason(reason)
                .currentQuantity(newCurrentQty)
                .disposalReason(disposalReason)
                .build();

        // 4) 저장 후 반환
        return storeStockRepository.save(newLog);
    }

    // 편의 메서드들

    @Transactional
    public StoreStock inbound(Long storeItemNo, int qty) {
        return recordStockChange(storeItemNo, qty, "입고", null);
    }

    @Transactional
    public StoreStock sell(Long storeItemNo, int qty) {
        return recordStockChange(storeItemNo, -qty, "판매", null);
    }

    @Transactional
    public StoreStock dispose(Long storeItemNo, int qty, String reason) {
        return recordStockChange(storeItemNo, -qty, "폐기", reason);
    }
}
