package com.globalnest.be.petition.dto.response;


import java.time.LocalDate;

public record PetitionResponse(

    Long id,
    String title,
    String petitionType,
    LocalDate createdDate,
    LocalDate agreementDeadline,
    Integer agreementCount

) {
}
