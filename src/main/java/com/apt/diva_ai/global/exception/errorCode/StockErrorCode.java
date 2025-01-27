package com.apt.diva_ai.global.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StockErrorCode implements ErrorCode {

    STOCK_CODE_NOT_FOUND(HttpStatus.NOT_FOUND,
        "존재하지 않는 종목 코드입니다");

    private final HttpStatus httpStatus;
    private final String message;
}
