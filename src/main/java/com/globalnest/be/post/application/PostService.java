package com.globalnest.be.post.application;

import com.globalnest.be.post.application.type.SortType;
import com.globalnest.be.post.dto.response.PostResponse;
import com.globalnest.be.post.dto.response.PostResponseList;
import com.globalnest.be.post.repository.PostRepository;
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

    public PostResponseList findPostResponseList(int page, int size, SortType sortType) {
        List<PostResponse> postResponseList = postRepository.findPostResponseList(page, size, sortType);

        boolean hasNext = postResponseList.size() == size + 1;

        // 마지막 원소를 제외한 서브 리스트 생성
        if (hasNext) {
            postResponseList = postResponseList.subList(0, postResponseList.size() - 1);
        }

        return PostResponseList.of(hasNext, page, size, sortType, postResponseList);
    }
}
