package com.globalnest.be.translation.dto;

import lombok.Builder;

@Builder
public record Message(
    String role,
    String content
) {
}
