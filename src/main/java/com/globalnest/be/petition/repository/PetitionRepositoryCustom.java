package com.globalnest.be.petition.repository;

import com.globalnest.be.petition.domain.type.PetitionType;
import com.globalnest.be.petition.dto.request.PetitionSortRequest;
import com.globalnest.be.petition.dto.response.PetitionDetailResponse;
import com.globalnest.be.petition.dto.response.PetitionResponse;

import java.util.List;

public interface PetitionRepositoryCustom {
    PetitionDetailResponse getPetitionDetail(Long petitionId, Long userId);

    List<PetitionResponse> getPetitionResponses(Boolean includeExpired, PetitionType petitionType,
                                                Boolean sortByAgreementCount, int page, int size);
}
