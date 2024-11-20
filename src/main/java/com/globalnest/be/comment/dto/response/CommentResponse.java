package com.globalnest.be.comment.dto.response;

import java.time.LocalDateTime;

public record CommentResponse(
        String profileImageUrl,
        String nickname,
        String content,
        LocalDateTime createdAt
) {
}
