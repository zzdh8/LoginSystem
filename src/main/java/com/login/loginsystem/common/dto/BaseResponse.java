package com.login.loginsystem.common.dto;


import com.login.loginsystem.common.exception.ErrorCode;
import com.login.loginsystem.common.exception.SuccessCode;
import lombok.*;


@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class BaseResponse<T> {
    private final int code;
    private final String message;
    private T data;

    public static BaseResponse success(SuccessCode successCode) {
        return new BaseResponse<>(successCode.getHttpStatusCode(), successCode.getMessage());
    }

    public static <T> BaseResponse<T> success(SuccessCode successCode, T data) {
        return new BaseResponse<T>(successCode.getHttpStatusCode(), successCode.getMessage(), data);
    }

    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode.getHttpStatusCode(), errorCode.getMessage());
    }

    public static BaseResponse error(ErrorCode errorCode, String message) {
        return new BaseResponse<>(errorCode.getHttpStatusCode(), message);
    }
}
