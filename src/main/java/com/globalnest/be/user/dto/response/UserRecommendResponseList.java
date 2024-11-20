package com.globalnest.be.user.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record UserRecommendResponseList(
        Boolean hasNext,
        int size,
        int page,
        List<UserRecommendResponse> userRecommendResponseList
) {

    public static UserRecommendResponseList of(
            Boolean hasNext, int size, int page, List<UserRecommendResponse> userRecommendResponseList
    ) {
        return UserRecommendResponseList.builder()
                .hasNext(hasNext)
                .size(size)
                .page(page)
                .userRecommendResponseList(userRecommendResponseList)
                .build();
    }
}
