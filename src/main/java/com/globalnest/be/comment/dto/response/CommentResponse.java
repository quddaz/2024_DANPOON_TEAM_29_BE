package com.globalnest.be.comment.dto.response;

import java.time.LocalDateTime;

public record CommentResponse(
        Long userId,
        String profileImageUrl,
        String nickname,
        Long commentId,
        String content,
        LocalDateTime createdAt
) {
}
