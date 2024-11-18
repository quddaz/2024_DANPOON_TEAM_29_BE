package com.globalnest.be.translation.dto.response;

import com.globalnest.be.translation.dto.Choice;
import lombok.Builder;

@Builder
public record ChatResponse(
    Choice[] choices
) {
}
