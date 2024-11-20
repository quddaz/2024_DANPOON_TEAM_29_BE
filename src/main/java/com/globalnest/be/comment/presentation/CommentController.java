package com.globalnest.be.comment.presentation;

import com.globalnest.be.comment.application.CommentService;
import com.globalnest.be.comment.dto.response.CommentResponseList;
import com.globalnest.be.global.dto.ResponseTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Comment", description = "댓글 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "게시글 댓글 페이징", description = "게시글의 댓글을 추가 조회합니다.<br>"
            + "/posts/{postId} API에서 마지막 댓글 ID를 기준으로 추가 조회합니다")
    @GetMapping("/{postId}")
    public ResponseEntity<ResponseTemplate<?>> getPostDetail(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0") Long lastCommentId,
            @RequestParam(defaultValue = "5") int size
    ) {
        CommentResponseList commentResponseList = commentService.getCommentResponseList(postId, lastCommentId, size);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(commentResponseList));
    }
}
