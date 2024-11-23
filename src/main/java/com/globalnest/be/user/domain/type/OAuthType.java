package com.globalnest.be.user.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OAuthType {
    KAKAO("kakao"),
    NAVER("naver"),
    GOOGLE("google"),
    ;

    private final String key;
}