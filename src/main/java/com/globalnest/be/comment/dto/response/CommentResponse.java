package com.globalnest.be.comment.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record CommentResponse(
        Long userId,
        String profileImageUrl,
        String nickname,
        Long commentId,
        String content,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt
) {
}
