package com.globalnest.be.petition.exception.errorcode;

import com.globalnest.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AgreementErrorCode implements ErrorCode {
    AGREEMENT_DUPLICATE(HttpStatus.BAD_REQUEST, "Duplicate agreement submission")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}