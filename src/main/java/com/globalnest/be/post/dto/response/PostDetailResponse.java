package com.globalnest.be.post.dto.response;

import com.globalnest.be.comment.dto.response.CommentResponse;
import java.util.List;

public record PostDetailResponse(
        boolean hasNext,
        int size,
        PostResponse postResponse,
        List<CommentResponse> commentResponseList
) {

    public static PostDetailResponse of(boolean hasNext, int size, PostResponse postResponse, List<CommentResponse> commentResponseList) {
        return new PostDetailResponse(hasNext, size, postResponse, commentResponseList);
    }
}
