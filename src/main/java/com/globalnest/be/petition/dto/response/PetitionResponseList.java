package com.globalnest.be.petition.dto.response;

import com.globalnest.be.petition.dto.request.PetitionSortRequest;
import lombok.Builder;

import java.util.List;

@Builder
public record PetitionResponseList(
        boolean hasNext,
        int page,
        int size,
        PetitionSortRequest petitionSortType,
        List<PetitionResponse> PetitionResponseList
) {

    public static PetitionResponseList of(
            boolean hasNext, int page, int size, PetitionSortRequest petitionSortType,
            List<PetitionResponse> petitionResponseList
    ) {
        return com.globalnest.be.petition.dto.response.PetitionResponseList.builder()
                .hasNext(hasNext)
                .page(page)
                .size(size)
                .petitionSortType(petitionSortType)
                .PetitionResponseList(petitionResponseList)
                .build();
    }
}