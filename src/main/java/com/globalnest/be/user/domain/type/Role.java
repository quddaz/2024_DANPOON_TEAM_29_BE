package com.globalnest.be.user.domain.type;

import lombok.Getter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER"),
    GUEST("ROLE_GUEST"),
    ;

    private final String key;
}