package com.globalnest.be.post.presentation;

import com.globalnest.be.global.dto.ResponseTemplate;
import com.globalnest.be.post.application.PostService;
import com.globalnest.be.post.application.type.SortType;
import com.globalnest.be.post.dto.response.PostResponseList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Post", description = "게시글 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 리스트 조회", description = "게시글 리스트를 조회합니다<br>"
            + "페이지 번호, 페이지 크기, 정렬 방식을 입력받아 게시글 리스트를 반환합니다<br>"
            + "page는 0번부터 시작")
    @GetMapping
    public ResponseEntity<ResponseTemplate<?>> getNearbyLecturePlaces(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam SortType sortType,
            @RequestParam Long userId
    ) {
        PostResponseList postResponseList = postService.findPostResponseList(userId, page, size, sortType);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(postResponseList));
    }
}
