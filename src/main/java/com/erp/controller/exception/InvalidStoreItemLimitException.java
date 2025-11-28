package com.erp.controller.exception;

/**
 * 하한선 값(newLimit)이 0 이하 등 비정상일 때 사용.
 * (null 은 '하한선 제거' 의미로 허용)
 */
public class InvalidStoreItemLimitException extends RuntimeException {

    public InvalidStoreItemLimitException(Integer value) {
        super("하한선은 1 이상의 정수여야 합니다. 입력 값 = " + value);
    }
}
