package com.apt.diva_ai.global.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ScriptErrorCode implements ErrorCode {

    SHELL_SCRIPT_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR,
        "쉘 스크립트 파일이 존재하지 않습니다"),
    SHELL_SCRIPT_PERMISSION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
        "쉘 스크립트 파일 실행 권한이 없습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
