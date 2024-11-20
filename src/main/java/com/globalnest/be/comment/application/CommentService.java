package com.globalnest.be.comment.application;

import com.globalnest.be.comment.dto.response.CommentResponse;
import com.globalnest.be.comment.dto.response.CommentResponseList;
import com.globalnest.be.comment.repository.CommentRepository;
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
}
