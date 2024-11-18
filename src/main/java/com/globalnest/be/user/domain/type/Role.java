package com.globalnest.be.user.domain.type;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Role {
    //스프링 시큐리티의 권한 코드는 항상 "ROLE_" 로 시작해야 함 -> hasRole로 뒤의 ADMIN, MEMBER로 접근 가능
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER"),
    GUEST("ROLE_GUEST"),
    ;

    private static final Map<String, Role> BY_KEY = Collections.unmodifiableMap(
        Stream.of(values()).collect(Collectors.toMap(Role::getKey, Function.identity())));

    private final String key;

    Role(String key) {
        this.key = key;
    }
}