package com.globalnest.be.user.domain.type;

import lombok.Getter;

@Getter
public enum AgeRange {
    NULL("미상"),
    TEENS("10"),
    TWENTIES("20"),
    THIRTIES("30"),
    FORTIES("40"),
    FIFTIES_AND_ABOVE("50UP");

    private final String description;

    AgeRange(String description) {
        this.description = description;
    }
}