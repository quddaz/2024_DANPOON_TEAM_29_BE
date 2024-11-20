package com.globalnest.be.comment.dto.response;

import java.util.List;

public record CommentResponseList(
        Boolean hasNext,
        int size,
        List<CommentResponse> commentResponseList
) {

    public static CommentResponseList of(Boolean hasNext, int size, List<CommentResponse> commentResponseList) {
        return new CommentResponseList(hasNext, size, commentResponseList);
    }
}
