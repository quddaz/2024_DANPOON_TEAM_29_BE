package com.globalnest.be.comment.dto.request;

import com.globalnest.be.comment.domain.Comment;
import com.globalnest.be.post.domain.Post;
import com.globalnest.be.user.domain.User;

public record CommentWriteRequest(
        String content
) {

    public Comment toEntity(User user, Post post) {
        return Comment.builder()
                .content(content)
                .post(post)
                .user(user)
                .build();
    }
}
