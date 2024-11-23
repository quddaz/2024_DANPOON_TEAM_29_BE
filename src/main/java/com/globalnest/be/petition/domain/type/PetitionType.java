package com.globalnest.be.petition.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PetitionType {
    WORKING_CONDITIONS("노동 환경 개선"),
    WAGES_AND_BENEFITS("임금 및 복지"),
    HOUSING_ISSUES("주거 문제"),
    LEGAL_PROTECTION("법적 보호 및 권리"),
    HEALTHCARE("의료 및 건강 관리"),
    EDUCATION_AND_ADAPTATION("교육 및 한국 생활 적응"),
    DISCRIMINATION_PREVENTION("차별 및 부당 대우 방지"),
    SOCIAL_RIGHTS_IMPROVEMENT("사회적 권리 향상");

    private final String description;
}