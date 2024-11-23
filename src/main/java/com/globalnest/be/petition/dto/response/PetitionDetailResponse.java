package com.globalnest.be.petition.dto.response;

import java.time.LocalDate;

public record PetitionDetailResponse(
        String title,
        String purpose,
        String content,
        String petitionType,
        String name,
        Integer agreementCount,
        LocalDate createdDate,
        LocalDate agreementDeadline,
        //청원 동의 여부
        boolean isAgreement,
        //내 청원인지 여부
        boolean isMyPetition
) {
}
