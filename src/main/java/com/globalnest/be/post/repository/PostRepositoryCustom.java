package com.globalnest.be.post.repository;

import com.globalnest.be.post.application.type.SortType;
import com.globalnest.be.post.dto.response.PostResponse;
import java.util.List;

public interface PostRepositoryCustom {

    List<PostResponse> findPostResponseList(int page, int size, SortType sortType);
}
