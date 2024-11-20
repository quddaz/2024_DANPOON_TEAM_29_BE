package com.globalnest.be.post.dto.response;

import lombok.Builder;

@Builder
public record AuthorSimpleInfoResponse(
        Long authorId,
        String authorNickName,
        String authorProfileImageUrl
) {
}