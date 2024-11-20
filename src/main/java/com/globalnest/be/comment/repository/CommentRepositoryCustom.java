package com.globalnest.be.comment.repository;

import com.globalnest.be.comment.dto.response.CommentResponse;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepositoryCustom {

    List<CommentResponse> findCommentResponseList(Long postId, Long lastCommentId, int size);
}
