package com.globalnest.be.user.exception.errorCode;

import com.globalnest.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
    USER_BIRTH_NOT_FOUND(HttpStatus.NOT_FOUND, "user birth not found");

    private final HttpStatus httpStatus;
    private final String message;
}