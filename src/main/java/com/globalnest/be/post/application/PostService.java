package com.globalnest.be.post.application;

import com.globalnest.be.comment.dto.response.CommentResponse;
import com.globalnest.be.comment.repository.CommentRepository;
import com.globalnest.be.global.application.AWSStorageService;
import com.globalnest.be.post.application.type.SortType;
import com.globalnest.be.post.domain.Post;
import com.globalnest.be.post.domain.PostTag;
import com.globalnest.be.post.domain.type.Tag;
import com.globalnest.be.post.dto.request.PostUploadRequest;
import com.globalnest.be.post.dto.response.PostDetailResponse;
import com.globalnest.be.post.repository.dto.PostRepoResponse;
import com.globalnest.be.post.dto.response.PostResponse;
import com.globalnest.be.post.dto.response.PostResponseList;
import com.globalnest.be.post.repository.PostRepository;
import com.globalnest.be.post.repository.PostTagRepository;
import com.globalnest.be.user.domain.User;
import com.globalnest.be.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final AWSStorageService awsStorageService;

    public PostResponseList findPostResponseList(Long userId, int page, int size, SortType sortType) {
        List<PostRepoResponse> postRepoResponseList = postRepository.findPostResponseList(userId, page, size, sortType);

        boolean hasNext = postRepoResponseList.size() == size + 1;

        // 마지막 원소를 제외한 서브 리스트 생성
        if (hasNext) {
            postRepoResponseList = postRepoResponseList.subList(0, postRepoResponseList.size() - 1);
        }

        List<PostResponse> postResponseList = postRepoResponseList.stream()
                .map(postRepoResponse -> {
                    List<Tag> list = postTagRepository.findAllByPostId(postRepoResponse.postId())
                            .stream()
                            .map(PostTag::getTag)
                            .toList();

                    return PostResponse.of(postRepoResponse, list);
                })
                .toList();

        return PostResponseList.of(hasNext, page, size, sortType, postResponseList);
    }

    public PostDetailResponse findPostDetailResponse(
            Long userId, Long postId, Long lastCommentId, int size
    ) {
        PostRepoResponse postRepoResponse = postRepository.findPostDetailResponse(userId, postId);

        List<Tag> list = postTagRepository.findAllByPostId(postId)
                .stream()
                .map(PostTag::getTag)
                .toList();

        PostResponse postResponse = PostResponse.of(postRepoResponse, list);

        List<CommentResponse> commentResponseList =
                commentRepository.findCommentResponseList(postId, lastCommentId, size);

        boolean hasNext = commentResponseList.size() == size + 1;

        // 마지막 원소를 제외한 서브 리스트 생성
        if (hasNext) {
            commentResponseList = commentResponseList.subList(0, commentResponseList.size() - 1);
        }

        return PostDetailResponse.of(hasNext, size, postResponse, commentResponseList);
    }

    @Transactional
    public void uploadPost(Long userId, PostUploadRequest request, MultipartFile file) {
        String imageUrl = "";
        if (file != null) {
            imageUrl = awsStorageService.uploadFile(file, "post");
        }

        User user = userService.findUserById(userId);

        Post newPost = request.toEntity(user, imageUrl);

        postRepository.save(newPost);
    }
}
