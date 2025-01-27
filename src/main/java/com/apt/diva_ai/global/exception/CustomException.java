package com.apt.diva_ai.global.exception;

import com.apt.diva_ai.global.exception.errorCode.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, String message) {
        super(errorCode.getMessage() + " " + message);
        this.errorCode = errorCode;
    }

    public CustomException(String message) {
        super(message);
        errorCode = null;
    }

    public ErrorResponse toResponse() {
        if (errorCode == null) {
            return ErrorResponse.builder().status(500).message(getMessage()).build();
        }

        return ErrorResponse.builder().status(errorCode.getHttpStatus().value())
            .message(errorCode.getMessage()).build();
    }
}
