package com.login.loginsystem.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    /*201 CREATED*/
    USER_JOIN_SUCCESS(HttpStatus.CREATED, "유저 회원가입 성공"),


    /*200 OK*/
    USER_LOGIN_SUCCESS(HttpStatus.OK, "유저 로그인 성공"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }
}