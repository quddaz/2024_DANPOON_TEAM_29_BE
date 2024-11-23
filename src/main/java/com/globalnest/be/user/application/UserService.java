package com.globalnest.be.user.application;

import com.globalnest.be.global.application.AWSStorageService;
import com.globalnest.be.user.domain.Subscribe;
import com.globalnest.be.user.domain.User;
import com.globalnest.be.user.dto.request.FirstLoginRequest;
import com.globalnest.be.user.dto.response.UserRecommendResponse;
import com.globalnest.be.user.dto.response.UserRecommendResponseList;
import com.globalnest.be.user.exception.UserNotFoundException;
import com.globalnest.be.user.exception.errorCode.UserErrorCode;
import com.globalnest.be.user.repository.SubscribeRepository;
import com.globalnest.be.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final AWSStorageService awsStorageService;

    /**
     * 첫 로그인 시 정보를 받아오는 메소드
     */
    public void registerUser(FirstLoginRequest request, Long id, MultipartFile file) {
        // 프로필 이미지 업로드 처리
        String imageUrl = file != null ? awsStorageService.uploadFile(file, "user") : "";

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));

        // 사용자 정보 업데이트
        user.updateProfile(request, imageUrl);

        userRepository.save(user);
    }

    @Transactional
    public void subscribeUser(Long userId, Long targetUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));
        User targetUser = userRepository.findById(targetUserId)
                .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));

        subscribeRepository.save(Subscribe.of(targetUser, user));
    }

    public UserRecommendResponseList findUserRecommendList(
            Long userId, int size, int page
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));

        List<UserRecommendResponse> userRecommendList =
                userRepository.findUserRecommendList(userId, user.getPart(), user.getAgeRange(), size, page);

        boolean hasNext = userRecommendList.size() == size + 1;

        if (hasNext) {
            userRecommendList = userRecommendList.subList(0, userRecommendList.size() - 1);
        }

        return UserRecommendResponseList.of(hasNext, size, page, userRecommendList);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));
    }
}
