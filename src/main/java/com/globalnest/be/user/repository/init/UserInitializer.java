package com.globalnest.be.user.repository.init;

import com.globalnest.be.global.util.DummyDataInit;
import com.globalnest.be.user.domain.User;
import com.globalnest.be.user.domain.type.AgeRange;
import com.globalnest.be.user.domain.type.Language;
import com.globalnest.be.user.domain.type.OAuthType;
import com.globalnest.be.user.domain.type.Part;
import com.globalnest.be.user.domain.type.Role;
import com.globalnest.be.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

@Slf4j
@RequiredArgsConstructor
@Order(1)
@DummyDataInit
public class UserInitializer implements ApplicationRunner {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final UserRepository userRepository;

    private static final String DUMMY_PROFILE_IMAGE_URL = "/profile/ic_profile.svg";

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() > 0) {
            log.info("[User] 더미 데이터 존재");
        } else {
            List<User> userList = new ArrayList<>();

            // 각 Part에 대한 더미 사용자 생성
            for (Part part : Part.values()) {
                User user = User.builder()
                        .email(part.name().toLowerCase() + "@naver.com") // 직종 이름을 기반으로 이메일 생성
                        .name(part.getCategory() + " 사용자") // 직종 이름을 사용하여 이름 생성
                        .nickName(part.name().toLowerCase()) // 직종 이름을 사용하여 닉네임 생성
                        .language(Language.ENGLISH) // 모든 사용자에게 동일한 언어 설정
                        .oAuthType(OAuthType.KAKAO)
                        .socialId("123456789" + part.ordinal()) // 각 Part의 인덱스를 사용하여 socialId 생성
                        .part(part) // 현재 Part 설정
                        .profileImage(
                                "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com" + DUMMY_PROFILE_IMAGE_URL)
                        .isAlarmAllowed(true)
                        .role(Role.MEMBER) // 모든 사용자는 일반 사용자로 설정
                        .ageRange(AgeRange.FORTIES) // 연령대 설정
                        .build();

                userList.add(user);
            }

            userRepository.saveAll(userList);
            log.info("[User] 더미 데이터 삽입 완료");
        }
    }
}
