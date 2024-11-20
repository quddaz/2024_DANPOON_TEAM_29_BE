package com.globalnest.be.comment.application;

import com.globalnest.be.comment.dto.request.CommentWriteRequest;
import com.globalnest.be.comment.dto.response.CommentResponse;
import com.globalnest.be.comment.dto.response.CommentResponseList;
import com.globalnest.be.comment.repository.CommentRepository;
import com.globalnest.be.post.application.PostService;
import com.globalnest.be.post.domain.Post;
import com.globalnest.be.user.domain.User;
import com.globalnest.be.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentResponseList getCommentResponseList(
            Long postId, Long lastCommentId, int size
    ) {
        List<CommentResponse> commentResponseList =
                commentRepository.findCommentResponseList(postId, lastCommentId, size);

        boolean hasNext = commentResponseList.size() == size + 1;

        // 마지막 원소를 제외한 서브 리스트 생성
        if (hasNext) {
            commentResponseList = commentResponseList.subList(0, commentResponseList.size() - 1);
        }

        return CommentResponseList.of(hasNext, commentResponseList.size(), commentResponseList);
    }

    @Transactional
    public void writeComment(Long userId, Long postId, CommentWriteRequest request) {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        commentRepository.save(request.toEntity(user, post));
    }
}
