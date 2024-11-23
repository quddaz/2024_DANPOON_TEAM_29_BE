package com.globalnest.be.oauth.exception;

import com.globalnest.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginTypeNotSupportException extends RuntimeException {
    private final ErrorCode errorCode;
}