package com.globalnest.be.post.dto.response;

import com.globalnest.be.comment.dto.response.CommentResponse;
import java.util.List;

public record PostDetailResponse(
        PostResponse postResponse,
        List<CommentResponse> commentResponseList
) {
}
