package com.globalnest.be.translation.dto.response;

import lombok.Builder;

@Builder
public record ChatResponse(
    Choice[] choices
) {
}
