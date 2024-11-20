package com.globalnest.be.petition.repository;

import com.globalnest.be.petition.domain.QAgreement;
import com.globalnest.be.petition.domain.QPetition;
import com.globalnest.be.petition.dto.response.PetitionResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PetitionRepositoryImpl implements PetitionRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<PetitionResponse> getPetitionResponses() {
        QPetition petition = QPetition.petition;
        QAgreement agreement = QAgreement.agreement;

        return queryFactory
            .select(Projections.constructor(
                PetitionResponse.class,
                petition.title,
                petition.petitionType,
                petition.createdDate,
                petition.agreementDeadline,
                agreement.count().intValue() // Agreement 개수
            ))
            .from(petition)
            .leftJoin(agreement).on(agreement.petition.id.eq(petition.id)) // Petition과 Agreement 조인
            .groupBy(petition.id) // Petition 별로 그룹화
            .fetch();
    }
}
