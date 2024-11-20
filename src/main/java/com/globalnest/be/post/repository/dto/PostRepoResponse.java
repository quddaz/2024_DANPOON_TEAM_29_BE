package com.globalnest.be.post.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.globalnest.be.post.dto.response.AuthorSimpleInfoResponse;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PostRepoResponse(
        AuthorSimpleInfoResponse authorInfo,
        LocalDateTime createdAt,
        Long postId,
        String content,
        Integer likeCount,
        String postImageUrl,
        Boolean isLike
) {

    @JsonProperty("postImageUrl")
    public String getPostImageUrl() {
        return postImageUrl == null ? "" : postImageUrl;
    }
}
