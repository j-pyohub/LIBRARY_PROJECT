package com.erp.controller.exception;

/**
 * 재고 조회에서 직영점 번호(storeNo)가 선택되지 않았을 때 사용.
 */
public class StoreNotSelectedException extends RuntimeException {

    public StoreNotSelectedException() {
        super("직영점을 선택해야 합니다.");
    }
}
