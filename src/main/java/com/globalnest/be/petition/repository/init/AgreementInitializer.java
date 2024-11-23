package com.globalnest.be.petition.repository.init;

import com.globalnest.be.global.util.DummyDataInit;
import com.globalnest.be.petition.domain.Agreement;
import com.globalnest.be.petition.domain.Petition;
import com.globalnest.be.petition.repository.AgreementRepository;
import com.globalnest.be.petition.repository.PetitionRepository;
import com.globalnest.be.user.domain.User;
import com.globalnest.be.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

@Slf4j
@RequiredArgsConstructor
@Order(3)
@DummyDataInit
public class AgreementInitializer implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;
    private final AgreementRepository agreementRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (agreementRepository.count() > 0) {
            log.info("[Agreement]더미 데이터 존재");
        } else {
            User DUMMY_USER = userRepository.findById(1L).orElseThrow();
            Petition DUMMY_PETITION = petitionRepository.findById(1L).orElseThrow();

            List<Agreement> agreementList = new ArrayList<>();

            Agreement agreement = Agreement.builder()
                    .user(DUMMY_USER)
                    .petition(DUMMY_PETITION)
                    .build();

            agreementList.add(agreement);

            agreementRepository.saveAll(agreementList);
        }
    }
}
