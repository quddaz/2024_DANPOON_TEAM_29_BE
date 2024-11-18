package com.globalnest.be.user.domain.type;

import lombok.Getter;

@Getter
public enum Part {
    PLANNING("기획·전략"),
    LEGAL("법무·사무·총무"),
    HR("인사·HR"),
    ACCOUNTING("회계·세무"),
    MARKETING("마케팅·광고·MD"),
    DEVELOPMENT("개발·데이터"),
    DESIGN("디자인"),
    LOGISTICS("물류·무역"),
    TRANSPORT("운전·운송·배송"),
    SALES("영업"),
    CUSTOMER_SERVICE("고객상담·TM"),
    FINANCE("금융·보험"),
    FOOD_BEVERAGE("식·음료"),
    RETAIL("고객서비스·리테일"),
    ENGINEERING("엔지니어링·설계"),
    MANUFACTURING("제조·생산"),
    EDUCATION("교육"),
    CONSTRUCTION("건축·시설"),
    MEDICAL("의료·바이오"),
    MEDIA("미디어·문화·스포츠");

    private final String category; // 직종 이름

    Part(String category) {
        this.category = category;
    }
}