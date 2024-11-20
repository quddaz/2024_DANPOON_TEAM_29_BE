package com.globalnest.be.post.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.globalnest.be.post.domain.type.Tag;
import com.globalnest.be.post.repository.dto.PostRepoResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record PostResponse(
        AuthorSimpleInfoResponse authorInfo,
        LocalDateTime createdAt,
        Long postId,
        String content,
        Integer likeCount,
        String postImageUrl,
        Boolean isLike,
        List<Tag> tags
) {

    public static PostResponse of(
            PostRepoResponse response, List<Tag> tags
    ) {
        return PostResponse.builder()
                .authorInfo(response.authorInfo())
                .createdAt(response.createdAt())
                .postId(response.postId())
                .content(response.content())
                .likeCount(response.likeCount())
                .postImageUrl(response.postImageUrl())
                .isLike(response.isLike())
                .tags(tags)
                .build();
    }

    @JsonProperty("postImageUrl")
    public String getPostImageUrl() {
        return postImageUrl == null ? "" : postImageUrl;
    }
}
