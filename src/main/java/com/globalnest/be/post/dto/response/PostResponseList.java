package com.globalnest.be.post.dto.response;

import com.globalnest.be.post.application.type.SortType;
import java.util.List;
import lombok.Builder;

@Builder
public record PostResponseList(
        boolean hasNext,
        int page,
        int size,
        SortType sortType,
        List<PostResponse> postResponseList
) {

    public static PostResponseList of(
            boolean hasNext, int page, int size, SortType sortType, List<PostResponse> postResponseList
    ) {
        return PostResponseList.builder()
                .hasNext(hasNext)
                .page(page)
                .size(size)
                .sortType(sortType)
                .postResponseList(postResponseList)
                .build();
    }
}
