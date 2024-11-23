package com.globalnest.be.user.dto.response;

import com.globalnest.be.user.domain.type.AgeRange;
import com.globalnest.be.user.domain.type.Part;

public record UserRecommendResponse(
        Long userId,
        String profileImageUrl,
        String nickname,
        AgeRange ageRange,
        Part part
) {
}