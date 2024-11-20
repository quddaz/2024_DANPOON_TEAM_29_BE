package com.globalnest.be.post.application;

import com.globalnest.be.post.application.type.SortType;
import com.globalnest.be.post.domain.PostTag;
import com.globalnest.be.post.domain.type.Tag;
import com.globalnest.be.post.dto.response.PostRepoResponse;
import com.globalnest.be.post.dto.response.PostResponse;
import com.globalnest.be.post.dto.response.PostResponseList;
import com.globalnest.be.post.repository.PostRepository;
import com.globalnest.be.post.repository.PostTagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;

    public PostResponseList findPostResponseList(int page, int size, SortType sortType) {
        List<PostRepoResponse> postRepoResponseList = postRepository.findPostResponseList(page, size, sortType);

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
}
