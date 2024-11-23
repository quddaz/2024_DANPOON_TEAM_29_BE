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
import java.util.Random;

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

            // 50명 더미 사용자 생성
            for (int i = 0; i < 50; i++) {
                // 랜덤 나이, 직종, 언어 선택
                AgeRange ageRange = AgeRange.values()[new Random().nextInt(AgeRange.values().length)];
                Part part = Part.values()[new Random().nextInt(Part.values().length)];
                Language language = Language.values()[new Random().nextInt(Language.values().length)];

                // 이름과 닉네임 생성
                String name = generateName(language);
                String nickName = generateNickName(language);

                User user = User.builder()
                    .email(part.name().toLowerCase() + "_" + ageRange.name().toLowerCase() + "@" + language.name().toLowerCase() + ".com")
                    .name(name)
                    .nickName(nickName)
                    .language(language)
                    .oAuthType(OAuthType.KAKAO)
                    .socialId("123456789_" + language.ordinal() + "_" + ageRange.ordinal() + "_" + part.ordinal())
                    .part(part)
                    .profileImage("https://" + bucket + ".s3.ap-northeast-2.amazonaws.com" + DUMMY_PROFILE_IMAGE_URL)
                    .isAlarmAllowed(true)
                    .role(Role.MEMBER)
                    .ageRange(ageRange)
                    .build();

                userList.add(user);
            }

            userRepository.saveAll(userList);
            log.info("[User] {}개의 더미 데이터 삽입 완료", userList.size());
        }
    }

    /**
     * 언어에 따른 실제 사람 이름 생성
     */
    private String generateName(Language language) {
        switch (language) {
            case VIETNAMESE:
                return getRandomVietnameseName();
            case CHINESE:
                return getRandomChineseName();
            case ENGLISH:
                return getRandomEnglishName();
            case TAGALOG:
                return getRandomTagalogName();
            case THAI:
                return getRandomThaiName();
            case MYANMAR:
                return getRandomMyanmarName();
            case KYRGYZ:
                return getRandomKyrgyzName();
            // 기본 영어 이름 설정
            default:
                return getRandomEnglishName();
        }
    }

    /**
     * 랜덤 영어 이름 생성
     */
    private String getRandomEnglishName() {
        String[] firstNames = {"John", "Michael", "David", "Chris", "James", "Robert", "William", "Daniel", "Joseph", "Charles",
            "Emma", "Olivia", "Sophia", "Ava", "Isabella", "Mia", "Amelia", "Harper", "Evelyn", "Abigail"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Taylor", "Miller", "Davis", "Wilson", "Moore", "Anderson", "Thomas",
            "Martinez", "Hernandez", "Lopez", "Gonzalez", "Clark", "Rodriguez", "Lewis", "Walker", "Perez", "Young"};
        return firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];
    }

    /**
     * 랜덤 베트남어 이름 생성
     */
    private String getRandomVietnameseName() {
        String[] firstNames = {"Nguyễn", "Trần", "Lê", "Phan", "Đặng", "Hoàng", "Vũ", "Bùi", "Đỗ", "Hồ"};
        String[] lastNames = {"Thị", "Tuấn", "Hồng", "Minh", "Lan", "Hoa", "Mai", "Anh", "Bích", "Tuyết"};
        return firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];
    }

    /**
     * 랜덤 중국어 이름 생성
     */
    private String getRandomChineseName() {
        String[] firstNames = {"李", "王", "张", "刘", "陈", "杨", "赵", "黄", "周", "吴"};
        String[] lastNames = {"伟", "芳", "娜", "敏", "梅", "杰", "磊", "强", "洋", "超"};
        return firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];
    }

    /**
     * 랜덤 타갈로그 이름 생성
     */
    private String getRandomTagalogName() {
        String[] firstNames = {"Juan", "Maria", "Jose", "Pedro", "Ana", "Carmen", "Antonio", "Carlos", "Rosa", "Lucia"};
        String[] lastNames = {"Santos", "Garcia", "Mendoza", "Lopez", "Reyes", "Ramos", "Fernandez", "Gonzalez", "Diaz", "Perez"};
        return firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];
    }

    /**
     * 랜덤 태국어 이름 생성
     */
    private String getRandomThaiName() {
        String[] firstNames = {"สมชาย", "สุนทร", "อภิชัย", "ณัฐพงษ์", "วุฒิชัย", "ธนกร", "ธวัชชัย", "พงษ์ศักดิ์", "ชาญชัย", "สมพร"};
        String[] lastNames = {"วัฒนชัย", "สมบัติ", "เกษม", "ศุภชัย", "จิตร", "สวัสดิ์", "พานิช", "ศรีสมบัติ", "กิตติ", "เทียม"};
        return firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];
    }

    /**
     * 랜덤 미얀마어 이름 생성
     */
    private String getRandomMyanmarName() {
        String[] firstNames = {"မောင်", "သူရ", "မေ", "သန်း", "ဝင်း", "နန်း", "ကျော်", "လှိုင်", "နိုင်", "အောင်"};
        String[] lastNames = {"ဝေ", "ကျော်", "တင်", "လွတ်", "ခင်", "မင်း", "ကန်", "နု", "ဝေ", "သင်း"};
        return firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];
    }

    /**
     * 랜덤 키르기즈 이름 생성
     */
    private String getRandomKyrgyzName() {
        String[] firstNames = {"Айбек", "Нурбек", "Канат", "Тимур", "Азамат", "Бекзат", "Максат", "Бекболот", "Самат", "Жандос"};
        String[] lastNames = {"Жамалиев", "Токтогулов", "Суранов", "Каныбеков", "Бекмурзаев", "Аманбаев", "Тынышбаев", "Молдобаев", "Курманбеков", "Жээнбеков"};
        return firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];
    }

    /**
     * 직종에 따른 닉네임 생성
     */
    private String generateNickName(Language language) {
        switch (language) {
            case VIETNAMESE:
                return "CôngNhân" + getRandomVietnameseName();
            case CHINESE:
                return "建筑工人" + getRandomChineseName();
            case ENGLISH:
                return "Worker" + getRandomEnglishName();
            case TAGALOG:
                return "Manggagawa" + getRandomTagalogName();
            case THAI:
                return "ช่าง" + getRandomThaiName();
            case MYANMAR:
                return "လယ်သမား" + getRandomMyanmarName();
            case KYRGYZ:
                return "Мыкты" + getRandomKyrgyzName();
            // 기본 닉네임 설정
            default:
                return "User" + getRandomEnglishName();
        }
    }
}
