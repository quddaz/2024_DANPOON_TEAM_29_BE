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
            log.info("[User]더미 데이터 존재");
        } else {
            List<User> userList = new ArrayList<>();

            User DUMMY_ADMIN = User.builder()
                    .email("admin@naver.com")
                    .name("관리자")
                    .nickName("admin")
                    .language(Language.KOREAN)
                    .oAuthType(OAuthType.KAKAO)
                    .socialId("1234567890")
                    .part(Part.AGRICULTURE)
                    .profileImage("https://" + bucket + ".s3.ap-northeast-2.amazonaws.com" + DUMMY_PROFILE_IMAGE_URL)
                    .isAlarmAllowed(true)
                    .role(Role.ADMIN)
                    .ageRange(AgeRange.FORTIES)
                    .build();

            User DUMMY_USER1 = User.builder()
                    .email("user@naver.com")
                    .name("유저")
                    .nickName("user")
                    .language(Language.KOREAN)
                    .oAuthType(OAuthType.KAKAO)
                    .socialId("123456789012")
                    .part(Part.AGRICULTURE)
                    .profileImage("https://" + bucket + ".s3.ap-northeast-2.amazonaws.com" + DUMMY_PROFILE_IMAGE_URL)
                    .isAlarmAllowed(true)
                    .role(Role.MEMBER)
                    .ageRange(AgeRange.FORTIES)
                    .build();

            User DUMMY_USER2 = User.builder()
                    .email("user2@naver.com")
                    .name("유저2")
                    .nickName("user2")
                    .language(Language.KOREAN)
                    .oAuthType(OAuthType.KAKAO)
                    .socialId("1234567890123")
                    .part(Part.AGRICULTURE)
                    .profileImage("https://" + bucket + ".s3.ap-northeast-2.amazonaws.com" + DUMMY_PROFILE_IMAGE_URL)
                    .isAlarmAllowed(true)
                    .role(Role.MEMBER)
                    .ageRange(AgeRange.FORTIES)
                    .build();

            userList.add(DUMMY_ADMIN);
            userList.add(DUMMY_USER1);
            userList.add(DUMMY_USER2);

            userRepository.saveAll(userList);
        }
    }
}
