package com.globalnest.be.petition.dto.request;

import com.globalnest.be.petition.domain.type.PetitionType;


public record PetitionSortRequest(
    Boolean includeExpired,       // 만료된 petition 포함 여부
    PetitionType petitionType,    // 카테고리 별 정렬을 위한 petitionType
    Boolean sortByAgreementCount // 동의자 수로 정렬할지 여부
) {

}