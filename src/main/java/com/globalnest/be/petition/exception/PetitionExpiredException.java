package com.globalnest.be.petition.exception;

import com.globalnest.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PetitionExpiredException extends RuntimeException {
    private final ErrorCode errorCode;
}