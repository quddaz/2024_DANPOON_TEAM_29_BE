package com.globalnest.be.post.repository;

import com.globalnest.be.post.application.type.SortType;
import com.globalnest.be.post.repository.dto.PostRepoResponse;
import java.util.List;

public interface PostRepositoryCustom {

    List<PostRepoResponse> findPostResponseList(Long userId, int page, int size, SortType sortType);
}
