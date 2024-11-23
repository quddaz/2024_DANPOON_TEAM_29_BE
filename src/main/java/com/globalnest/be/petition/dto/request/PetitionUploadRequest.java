package com.globalnest.be.petition.dto.request;

import com.globalnest.be.petition.domain.Petition;
import com.globalnest.be.petition.domain.type.PetitionType;
import com.globalnest.be.user.domain.User;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PetitionUploadRequest(
        @NotNull String title,
        @NotNull String purpose,
        @NotNull String content,
        @NotNull PetitionType petitionType,
        @NotNull LocalDate agreementDeadline
) {

    public Petition toEntity(User user) {
        return Petition.builder()
                .user(user)
                .title(title)
                .purpose(purpose)
                .content(content)
                .petitionType(petitionType)
                .agreementDeadline(agreementDeadline)
                .build();
    }
}