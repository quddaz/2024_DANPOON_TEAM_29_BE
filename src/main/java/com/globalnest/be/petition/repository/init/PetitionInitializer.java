package com.globalnest.be.petition.repository.init;

import com.globalnest.be.global.util.DummyDataInit;
import com.globalnest.be.petition.domain.Petition;
import com.globalnest.be.petition.domain.type.PetitionType;
import com.globalnest.be.petition.repository.PetitionRepository;
import com.globalnest.be.user.domain.User;
import com.globalnest.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(2)
@DummyDataInit
public class PetitionInitializer implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (petitionRepository.count() > 0) {
            log.info("[Petition]더미 데이터 존재");
        } else {
            User DUMMY_USER1 = userRepository.findById(2L).orElseThrow();
            User DUMMY_USER2 = userRepository.findById(3L).orElseThrow();

            List<Petition> petitions = new ArrayList<>();

            for (int i = 1; i <= 10; i++) {
                Petition petition = Petition.builder()
                        .user(i % 2 == 0 ? DUMMY_USER1 : DUMMY_USER2)
                        .title("제목 " + i)
                        .purpose("취지 " + i)
                        .content("내용 " + i)
                        .petitionType(PetitionType.values()[i % PetitionType.values().length])
                        .agreementDeadline(LocalDate.now().plusDays(i * 7))
                        .build();

                petitions.add(petition);
            }

            petitionRepository.saveAll(petitions);
        }
    }
}
