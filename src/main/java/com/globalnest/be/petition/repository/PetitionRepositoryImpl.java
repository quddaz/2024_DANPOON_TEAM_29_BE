package com.globalnest.be.petition.repository;

import com.globalnest.be.petition.dto.request.PetitionSortRequest;
import com.globalnest.be.petition.dto.response.PetitionDetailResponse;
import com.globalnest.be.petition.dto.response.PetitionResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.globalnest.be.petition.domain.QAgreement.agreement;
import static com.globalnest.be.petition.domain.QPetition.petition;
import static com.globalnest.be.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class PetitionRepositoryImpl implements PetitionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public PetitionDetailResponse getPetitionDetail(Long petitionId, Long userId) {

        return queryFactory
                .select(Projections.constructor(
                        PetitionDetailResponse.class,
                        petition.title,
                        petition.purpose,
                        petition.content,
                        petition.petitionType.stringValue(),
                        user.name,
                        agreement.count().intValue(),
                        petition.createdDate,
                        petition.agreementDeadline,
                        // Agreement 여부를 서브쿼리로 확인
                        JPAExpressions.selectOne()
                                .from(agreement)
                                .where(agreement.petition.id.eq(petition.id)
                                        .and(agreement.user.id.eq(userId)))
                                .exists(),
                        // MyPetition 여부를 서브쿼리로 확인
                        JPAExpressions.selectOne()
                                .from(petition)
                                .where(petition.user.id.eq(userId)
                                        .and(petition.id.eq(petitionId)))
                                .exists()
                ))
                .from(petition)
                .leftJoin(user).on(user.id.eq(petition.user.id))
                .leftJoin(agreement).on(agreement.petition.id.eq(petition.id))
                .where(petition.id.eq(petitionId))
                .fetchOne();
    }


    @Override
    public List<PetitionResponse> getPetitionResponses(PetitionSortRequest petitionSortRequest, int page, int size) {
        // 조건 정의
        BooleanBuilder conditions = createPetitionConditions(petitionSortRequest);

        return queryFactory
                .select(Projections.constructor(
                        PetitionResponse.class,
                        petition.id,
                        petition.title,
                        petition.petitionType.stringValue(),
                        petition.createdDate,
                        petition.agreementDeadline,
                        agreement.count().intValue() // Agreement 개수
                ))
                .from(petition)
                .leftJoin(agreement).on(agreement.petition.id.eq(petition.id)) // Petition과 Agreement 조인
                .groupBy(petition.id) // Petition 별로 그룹화
                .orderBy(orderSpecifier(petitionSortRequest)) // 정렬 조건 추가
                .where(conditions) // 미리 정의된 조건 적용
                .offset((long) page * size)
                .limit(size + 1)
                .fetch();
    }

    // 정렬 조건을 처리하는 메서드
    private OrderSpecifier<?>[] orderSpecifier(PetitionSortRequest petitionSortRequest) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        // 동의자 수 기준으로 정렬
        if (petitionSortRequest.sortByAgreementCount() != null && petitionSortRequest.sortByAgreementCount()) {
            orderSpecifiers.add(agreement.count().desc());
        }

        // 생성 날짜 기준으로 정렬
        orderSpecifiers.add(petition.createdDate.desc());

        // 마지막으로 아이디 기준으로 정렬
        orderSpecifiers.add(petition.id.desc());

        return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }

    // Petition 조건을 정의하는 메서드
    private BooleanBuilder createPetitionConditions(PetitionSortRequest petitionSortRequest) {
        BooleanBuilder conditions = new BooleanBuilder();

        // 만료된 Petition 제외
        if (petitionSortRequest.includeExpired() != null && !petitionSortRequest.includeExpired()) {
            conditions.and(petition.agreementDeadline.lt(LocalDate.now())); // 만료된 Petition 제외
        }

        // 카테고리로 검색
        if (petitionSortRequest.petitionType() != null) {
            conditions.and(petition.petitionType.eq(petitionSortRequest.petitionType()));
        }

        return conditions;
    }
}

