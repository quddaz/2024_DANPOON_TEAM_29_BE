package com.globalnest.be.user.repository;

import com.globalnest.be.user.domain.type.AgeRange;
import com.globalnest.be.user.domain.type.Part;
import com.globalnest.be.user.dto.response.UserRecommendResponse;
import java.util.List;

public interface UserRepositoryCustom {

    List<UserRecommendResponse> findUserRecommendList(
            Long userId, Part part, AgeRange ageRange, int size, int page);
}
