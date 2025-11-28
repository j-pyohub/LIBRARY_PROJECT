package com.erp.controller.exception;

/**
 * 본사에서 이미 하한선을 설정한 품목에 대해
 * 직영점이 하한선을 설정/변경하려고 할 때 사용.
 */
public class StoreItemLimitConflictException extends RuntimeException {

    public StoreItemLimitConflictException() {
        super("본사에서 이미 하한선을 설정한 품목입니다. 직영점에서는 수정할 수 없습니다.");
    }
}
