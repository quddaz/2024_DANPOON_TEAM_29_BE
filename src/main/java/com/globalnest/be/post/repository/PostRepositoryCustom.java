package com.globalnest.be.post.repository;

import com.globalnest.be.post.application.type.SortType;
import com.globalnest.be.post.repository.dto.PostRepoResponse;
import com.globalnest.be.user.domain.type.Part;
import java.util.List;

public interface PostRepositoryCustom {

    List<PostRepoResponse> findPostResponseList(Long userId, int page, int size, SortType sortType, Part part);
    PostRepoResponse findPostDetailResponse(Long userId, Long postId);
}
