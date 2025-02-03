package com.apt.diva_ai.global.exception.errorCode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    HttpStatus getHttpStatus();

    String getMessage();
}
