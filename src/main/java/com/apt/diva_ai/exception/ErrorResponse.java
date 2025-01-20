package com.apt.diva_ai.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class ErrorResponse {

    private final int status;
    private final String message;
}
