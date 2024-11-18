package com.globalnest.be.oauth.exception.errorcode;

import com.globalnest.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    LOGIN_TYPE_NOT_SUPPORT(HttpStatus.NOT_ACCEPTABLE, "login type not support."),
    TOKEN_NOT_VALID(HttpStatus.NOT_ACCEPTABLE, "refresh token is not valid."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}