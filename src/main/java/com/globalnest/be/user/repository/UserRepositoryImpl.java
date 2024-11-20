package com.globalnest.be.user.repository;

import static com.globalnest.be.user.domain.QUser.user;

import com.globalnest.be.user.domain.type.AgeRange;
import com.globalnest.be.user.domain.type.Part;
import com.globalnest.be.user.dto.response.UserRecommendResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserRecommendResponse> findUserRecommendList(
            Long userId, Part part, AgeRange ageRange, int size, int page
    ) {
        return jpaQueryFactory
                .select(Projections.constructor(UserRecommendResponse.class,
                        user.id,
                        user.profileImage,
                        user.nickName,
                        user.ageRange,
                        user.part
                ))
                .from(user)
                .where(user.id.ne(userId), partExpression(part), ageRangeExpression(ageRange))
                .offset((long) page * size)
                .limit(size + 1)
                .fetch();
    }

    private BooleanExpression partExpression(Part part) {
        return user.part.eq(part);
    }

    private BooleanExpression ageRangeExpression(AgeRange ageRange) {
        return user.ageRange.eq(ageRange);
    }
}
