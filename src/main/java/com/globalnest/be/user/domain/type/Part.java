package com.globalnest.be.user.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Part {
    MANUFACTURING("제조업"),
    CONSTRUCTION("건설업"),
    LOGISTICS("운전 및 운송"),
    SERVICE("서비스업"),
    AGRICULTURE("농업 및 축산업"),
    FISHERIES("어업"),
    HOUSECARE("가사 및 돌봄"),
    PROFESSIONAL("전문직");

    private final String category; // 직종 이름
}