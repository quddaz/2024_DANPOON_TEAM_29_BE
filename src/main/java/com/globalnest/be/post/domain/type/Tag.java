package com.globalnest.be.post.domain.type;

import com.globalnest.be.post.domain.Post;
import com.globalnest.be.post.domain.PostTag;

public enum Tag {
    SAD, HAPPY, LONELY, ANGRY, SATISFIED, UNSATISFIED;

    public PostTag toPostTag(Post post) {
        return PostTag.builder()
                .post(post)
                .tag(this)
                .build();
    }
}
