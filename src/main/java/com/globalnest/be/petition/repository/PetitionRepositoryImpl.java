package com.globalnest.be.petition.repository;

import com.globalnest.be.petition.domain.QAgreement;
import com.globalnest.be.petition.domain.QPetition;
import com.globalnest.be.petition.dto.request.PetitionSortRequest;
import com.globalnest.be.petition.dto.response.PetitionDetailResponse;
import com.globalnest.be.petition.dto.response.PetitionResponse;
import com.globalnest.be.user.domain.QUser;
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

@Repository
@RequiredArgsConstructor
public class PetitionRepositoryImpl implements PetitionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public PetitionDetailResponse getPetitionDetail(Long petitionId, Long userId) {
        QPetition petition = QPetition.petition;
        QAgreement agreement = QAgreement.agreement;
        QUser user = QUser.user;


        // Petition을 조회하는 쿼리
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
                // 서브쿼리로 Agreement 존재 여부 확인
                JPAExpressions.selectOne()
                    .from(agreement)
                    .where(agreement.petition.id.eq(petition.id)
                        .and(agreement.user.id.eq(userId)))
                    .exists()
            ))
            .from(petition)
            .leftJoin(user).on(user.id.eq(petition.user.id)) // Petition의 user와 User 엔티티 조인
            .leftJoin(agreement).on(agreement.petition.id.eq(petition.id)) // Petition과 Agreement를 조인
            .groupBy(petition.id, user.id) // Petition과 User로 그룹화
            .where(petition.id.eq(petitionId))
            .fetchOne();
    }

    @Override
    public List<PetitionResponse> getPetitionResponses(PetitionSortRequest petitionSortRequest, int page, int size) {
        QPetition petition = QPetition.petition;
        QAgreement agreement = QAgreement.agreement;

        // 조건 정의
        BooleanBuilder conditions = createPetitionConditions(petitionSortRequest);

        return queryFactory
            .select(Projections.constructor(
                PetitionResponse.class,
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

