package com.erp.controller.exception;

/**
 * storeItemNo로 재고 품목을 찾지 못했을 때 사용.
 */
public class StoreItemNotFoundException extends RuntimeException {

    public StoreItemNotFoundException(Long storeItemNo) {
        super("존재하지 않는 재고 품목입니다. storeItemNo=" + storeItemNo);
    }
}
