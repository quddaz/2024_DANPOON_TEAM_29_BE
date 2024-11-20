package com.globalnest.be.post.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PostResponse(
        AuthorSimpleInfoResponse authorInfo,
        LocalDateTime createdAt,
        Long postId,
        String content,
        Integer likeCount,
        String postImageUrl
) {
}
