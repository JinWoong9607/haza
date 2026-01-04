package com.gachi.haza.global.exception;

public class ResponseStatusException extends RuntimeException {
    private final ErrorCode errorCode;

    public ResponseStatusException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
