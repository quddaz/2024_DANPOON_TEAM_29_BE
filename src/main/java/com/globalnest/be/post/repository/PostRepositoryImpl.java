package com.globalnest.be.post.repository;

import static com.globalnest.be.post.domain.QPost.post;
import static com.globalnest.be.post.domain.QPostLike.postLike;

import com.globalnest.be.post.application.type.SortType;
import com.globalnest.be.post.dto.response.AuthorSimpleInfoResponse;
import com.globalnest.be.post.repository.dto.PostRepoResponse;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PostRepoResponse> findPostResponseList(Long userId, int page, int size, SortType sortType) {
        return jpaQueryFactory
                .select(Projections.constructor(PostRepoResponse.class,
                        Projections.constructor(AuthorSimpleInfoResponse.class,
                                post.user.id,
                                post.user.nickName,
                                post.user.profileImage
                        ), // 작성자 정보
                        post.createdDate, // 생성 날짜
                        post.id, // 포스트 ID
                        post.content, // 포스트 내용
                        post.postLikeList.size(), // 좋아요 수
                        post.postImageUrl, // 포스트 이미지 URL
                        // 유저가 해당 포스트를 좋아요 했는지 확인하는 서브쿼리
                        JPAExpressions.selectOne() // 서브쿼리
                                .from(postLike)
                                .where(postLike.post.eq(post)
                                        .and(postLike.user.id.eq(userId))) // 조건 추가
                                .exists() // 존재 여부 확인
                ))
                .from(post)
                .leftJoin(post.postLikeList) // 좋아요 리스트와 조인
                .leftJoin(post.user) // 작성자 정보와 조인
                .groupBy(post.id) // 포스트 ID로 그룹화
                .orderBy(orderSpecifier(sortType)) // 정렬
                .offset((long) page * size) // 페이지 번호에 따라 결과를 가져오도록 설정
                .limit(size + 1)
                .fetch();
    }


    private OrderSpecifier<?> orderSpecifier(SortType sortType) {
        return switch (sortType) {
            case CREATED_AT_DESC -> post.createdDate.desc();
            case LIKE_DESC -> post.postLikeList.size().desc();
        };
    }

    @Override
    public PostRepoResponse findPostDetailResponse(Long userId, Long postId) {
        return jpaQueryFactory
                .select(Projections.constructor(PostRepoResponse.class,
                        Projections.constructor(AuthorSimpleInfoResponse.class,
                                post.user.id,
                                post.user.nickName,
                                post.user.profileImage
                        ), // 작성자 정보
                        post.createdDate, // 생성 날짜
                        post.id, // 포스트 ID
                        post.content, // 포스트 내용
                        post.postLikeList.size(), // 좋아요 수
                        post.postImageUrl, // 포스트 이미지 URL
                        // 유저가 해당 포스트를 좋아요 했는지 확인하는 서브쿼리
                        JPAExpressions.selectOne() // 서브쿼리
                                .from(postLike)
                                .where(postLike.post.eq(post)
                                        .and(postLike.user.id.eq(userId))) // 조건 추가
                                .exists() // 존재 여부 확인
                ))
                .from(post)
                .where(post.id.eq(postId))
                .fetchOne();
    }
}
