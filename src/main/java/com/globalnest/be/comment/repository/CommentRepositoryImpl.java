package com.globalnest.be.comment.repository;

import static com.globalnest.be.comment.domain.QComment.comment;

import com.globalnest.be.comment.dto.response.CommentResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommentResponse> findCommentResponseList(Long postId, Long lastCommentId, int size) {

        return jpaQueryFactory
                .select(Projections.constructor(CommentResponse.class,
                        comment.user.id,
                        comment.user.profileImage,
                        comment.user.nickName,
                        comment.id,
                        comment.content,
                        comment.createdDate
                ))
                .from(comment)
                .leftJoin(comment.user)
                .where(comment.post.id.eq(postId), lastReviewIdExpression(lastCommentId))
                .orderBy(comment.id.desc())
                .limit(size + 1)
                .fetch();
    }

    private BooleanExpression lastReviewIdExpression(long lastCommentId) {
        return lastCommentId > 0 ? comment.id.lt(lastCommentId) : null;
    }
}
