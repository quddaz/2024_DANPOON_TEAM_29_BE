package com.globalnest.be.petition.dto.response;

import com.globalnest.be.petition.domain.type.PetitionType;
import com.globalnest.be.petition.dto.request.PetitionSortRequest;
import lombok.Builder;

import java.util.List;

@Builder
public record PetitionResponseList(
    boolean hasNext,
    int page,
    int size,
    Boolean includeExpired,       // 만료된 petition 포함 여부
    PetitionType petitionType,    // 카테고리 별 정렬을 위한 petitionType
    Boolean sortByAgreementCount, // 동의자 수로 정렬할지 여부
    List<PetitionResponse> PetitionResponseList
) {

    public static PetitionResponseList of(
        boolean hasNext, int page, int size, Boolean includeExpired,
        PetitionType petitionType, Boolean sortByAgreementCount,
        List<PetitionResponse> petitionResponseList
    ) {
        return com.globalnest.be.petition.dto.response.PetitionResponseList.builder()
            .hasNext(hasNext)
            .page(page)
            .size(size)
            .includeExpired(includeExpired)
            .petitionType(petitionType)
            .sortByAgreementCount(sortByAgreementCount)
            .PetitionResponseList(petitionResponseList)
            .build();
    }
}