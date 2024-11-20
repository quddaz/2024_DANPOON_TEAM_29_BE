package com.globalnest.be.post.dto.request;

import com.globalnest.be.post.domain.Post;
import com.globalnest.be.post.domain.type.Tag;
import com.globalnest.be.user.domain.User;
import java.util.List;

public record PostUploadRequest(
        String title,
        String content,
        List<Tag> tags
) {

    public Post toEntity(User user, String postImageUrl) {
        return Post.builder()
                .user(user)
                .title(title)
                .content(content)
                .postImageUrl(postImageUrl)
                .build();
    }
}
