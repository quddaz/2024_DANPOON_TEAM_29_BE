package com.globalnest.be.translation.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.globalnest.be.translation.dto.Choice;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record ChatResponse(
    Choice[] choices
) {
}
