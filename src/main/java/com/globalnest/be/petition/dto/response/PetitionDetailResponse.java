package com.globalnest.be.petition.dto.response;

import com.globalnest.be.petition.domain.type.PetitionType;

import java.time.LocalDate;

public record PetitionDetailResponse(
    String title,
    String purpose,
    String content,
    String petitionType,
    String name,
    Integer agreementCount,
    LocalDate createDate,
    LocalDate agreementDeadline,
    boolean isAgreement
) {
}
