package com.globalnest.be.petition.dto.response;

import com.globalnest.be.petition.domain.type.PetitionType;

import java.time.LocalDate;

public record PetitionResponse(
    String title,
    String petitionType,
    LocalDate createdDate,
    LocalDate agreementDeadline,
    Integer agreementCount

) {
}
