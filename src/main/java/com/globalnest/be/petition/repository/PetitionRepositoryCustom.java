package com.globalnest.be.petition.repository;

import com.globalnest.be.petition.dto.response.PetitionResponse;

import java.util.List;

public interface PetitionRepositoryCustom {
    List<PetitionResponse> getPetitionResponses();
}
