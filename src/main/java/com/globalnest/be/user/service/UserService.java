package com.globalnest.be.user.service;

import com.globalnest.be.user.domain.User;
import com.globalnest.be.user.dto.request.FirstLoginRequest;
import com.globalnest.be.user.exception.UserNotFoundException;
import com.globalnest.be.user.exception.errorCode.UserErrorCode;
import com.globalnest.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    /**
     * 첫 로그인 시 정보를 받아오는 메소드
     */
    public void registerUser(FirstLoginRequest request,String file_url, Long id){
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));
        user.setName(request.name());
        user.setNickName(request.nickname());
        user.setPart(request.part());
        user.setLanguage(request.language());
        user.setProfile_image(file_url);
        user.setAgeRange(request.ageRange());

        userRepository.save(user);
    }
}
