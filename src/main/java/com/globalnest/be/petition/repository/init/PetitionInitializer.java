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

            Petition petition1 = Petition.builder()
                .user(DUMMY_USER1)
                .title("안전하고 쾌적한 작업 환경을 요구합니다")
                .purpose("외국인 노동자들이 안전하고 건강하게 일할 수 있는 환경을 보장하기 위해.")
                .content("작업장 내 안전 장비와 위생 시설을 강화하고, 정기적인 안전 교육을 통해 사고를 예방할 수 있는 환경을 만들어 주시길 요청드립니다.")
                .petitionType(PetitionType.WORKING_CONDITIONS)
                .agreementDeadline(LocalDate.now().plusDays(7))
                .build();

            Petition petition2 = Petition.builder()
                .user(DUMMY_USER1)
                .title("공정한 임금과 복지 혜택 제공")
                .purpose("외국인 노동자들의 생계를 안정적으로 보장하기 위해.")
                .content("동일한 노동에 대해 정당한 임금을 받는 것은 기본적인 권리입니다. 또한, 건강보험, 퇴직금, 유급 휴가 등 복지 혜택이 내국인과 동등하게 제공되기를 바랍니다.")
                .petitionType(PetitionType.WAGES_AND_BENEFITS)
                .agreementDeadline(LocalDate.now().plusDays(14))
                .build();

            Petition petition3 = Petition.builder()
                .user(DUMMY_USER1)
                .title("안전하고 적합한 주거 환경 보장")
                .purpose("외국인 노동자들의 주거권을 보장하기 위해.")
                .content("외국인 노동자들이 거주하는 숙소는 종종 비위생적이고 과밀한 경우가 많습니다. 주거 환경을 개선하고, 법적 기준에 부합하는 숙소 제공을 의무화해 주시길 요청드립니다.")
                .petitionType(PetitionType.HOUSING_ISSUES)
                .agreementDeadline(LocalDate.now().plusDays(21))
                .build();

            Petition petition4 = Petition.builder()
                .user(DUMMY_USER1)
                .title("외국인 노동자 권리 강화")
                .purpose("법적 보호를 강화하여 외국인 노동자들의 인권을 보장하기 위해.")
                .content("계약 불이행, 부당 해고 등의 피해를 입는 사례가 빈번합니다. 외국인 노동자를 위한 법적 상담과 권리 보호 체계를 강화해 주시고, 피해를 입은 노동자가 법적으로 보호받을 수 있도록 지원해 주시길 바랍니다.")
                .petitionType(PetitionType.LEGAL_PROTECTION)
                .agreementDeadline(LocalDate.now().plusDays(28))
                .build();

            Petition petition5 = Petition.builder()
                .user(DUMMY_USER1)
                .title("의료 접근성과 건강 관리를 보장해주세요")
                .purpose("외국인 노동자들의 건강을 보호하기 위해.")
                .content("외국인 노동자들이 쉽게 의료 서비스를 받을 수 있도록 건강보험 적용을 확대하고, 직업병 예방을 위한 정기 검진과 지원이 이루어지길 요청드립니다.")
                .petitionType(PetitionType.HEALTHCARE)
                .agreementDeadline(LocalDate.now().plusDays(35))
                .build();

            Petition petition6 = Petition.builder()
                .user(DUMMY_USER1)
                .title("언어 교육과 생활 적응 지원 확대")
                .purpose("외국인 노동자들의 한국 생활 적응을 돕기 위해.")
                .content("한국에서 안정적으로 생활하려면 언어와 문화 이해가 필수입니다. 외국인 노동자들에게 한국어 교육과 생활 적응 프로그램을 확대 제공해 주시길 요청드립니다.")
                .petitionType(PetitionType.EDUCATION_AND_ADAPTATION)
                .agreementDeadline(LocalDate.now().plusDays(42))
                .build();

            Petition petition7 = Petition.builder()
                .user(DUMMY_USER1)
                .title("차별 없는 직장 문화를 만들어주세요")
                .purpose("외국인 노동자들이 차별 없이 일할 수 있는 환경 조성을 위해.")
                .content("외국인이라는 이유로 차별받거나 부당한 대우를 받는 사례가 많습니다. 차별 방지 교육을 강화하고, 이를 어길 경우 강력한 처벌이 이루어지길 요청드립니다.")
                .petitionType(PetitionType.DISCRIMINATION_PREVENTION)
                .agreementDeadline(LocalDate.now().plusDays(49))
                .build();

            Petition petition8 = Petition.builder()
                .user(DUMMY_USER1)
                .title("외국인 노동자의 목소리를 반영해주세요")
                .purpose("외국인 노동자들의 권리와 요구가 정책에 반영될 수 있도록.")
                .content("외국인 노동자의 의견을 수렴할 수 있는 정기적인 간담회를 마련하고, 그들의 목소리가 정책에 반영될 수 있는 시스템을 구축해 주시길 요청드립니다.")
                .petitionType(PetitionType.SOCIAL_RIGHTS_IMPROVEMENT)
                .agreementDeadline(LocalDate.now().plusDays(56))
                .build();

            Petition petition9 = Petition.builder()
                .user(DUMMY_USER1)
                .title("초과 근무에 대한 공정한 보상 요청")
                .purpose("외국인 노동자들이 초과 근무에 대해 정당한 대가를 받을 수 있도록.")
                .content("초과 근무와 야간 근무에 대해 적정한 수당을 지급하고, 이를 정기적으로 감시하여 노동 착취를 예방해 주시길 바랍니다.")
                .petitionType(PetitionType.WAGES_AND_BENEFITS)
                .agreementDeadline(LocalDate.now().plusDays(63))
                .build();

            Petition petition10 = Petition.builder()
                .user(DUMMY_USER1)
                .title("산재 보상 절차 간소화 요구")
                .purpose("외국인 노동자들이 산재 피해를 보상받기 쉽도록.")
                .content("산재를 입은 노동자들이 복잡한 행정 절차와 언어 장벽으로 인해 보상을 받지 못하는 일이 많습니다. 산재 보상 절차를 간소화하고, 다국어 지원 서비스를 강화해 주시길 요청드립니다.")
                .petitionType(PetitionType.HEALTHCARE)
                .agreementDeadline(LocalDate.now().plusDays(70))
                .build();
            petitions.add(petition1);
            petitions.add(petition2);
            petitions.add(petition3);
            petitions.add(petition4);
            petitions.add(petition5);
            petitions.add(petition6);
            petitions.add(petition7);
            petitions.add(petition8);
            petitions.add(petition9);
            petitions.add(petition10);



            petitionRepository.saveAll(petitions);
        }
    }
}
