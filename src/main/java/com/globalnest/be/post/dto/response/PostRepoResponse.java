package com.globalnest.be.post.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PostRepoResponse(
        AuthorSimpleInfoResponse authorInfo,
        LocalDateTime createdAt,
        Long postId,
        String content,
        Integer likeCount,
        String postImageUrl
) {

    @JsonProperty("postImageUrl")
    public String getPostImageUrl() {
        return postImageUrl == null ? "" : postImageUrl;
    }
}
