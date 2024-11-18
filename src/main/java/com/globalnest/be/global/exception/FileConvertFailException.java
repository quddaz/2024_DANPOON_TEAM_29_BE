package com.globalnest.be.global.exception;

import com.globalnest.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FileConvertFailException extends RuntimeException {

    private final ErrorCode errorCode;
}
