package com.login.loginsystem.common.exception.model;

import com.login.loginsystem.common.exception.ErrorCode;
import lombok.Getter;
@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getHttpStatus() {
        return errorCode.getHttpStatusCode();
    }
}
