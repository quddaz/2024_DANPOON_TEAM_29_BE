package com.globalnest.be.oauth.service;

import com.globalnest.be.oauth.dto.CustomOAuth2User;
import com.globalnest.be.oauth.dto.social.GoogleResponse;
import com.globalnest.be.oauth.dto.social.KakaoResponse;
import com.globalnest.be.oauth.dto.social.NaverResponse;
import com.globalnest.be.oauth.dto.social.OAuth2Response;
import com.globalnest.be.oauth.exception.LoginTypeNotSupportException;
import com.globalnest.be.oauth.exception.errorcode.AuthErrorCode;
import com.globalnest.be.user.domain.User;
import com.globalnest.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public CustomOAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest); // OAuth2 사용자 정보를 가져옴

        // OAuth2Response를 제공업체에 따라 추출
        OAuth2Response oAuth2Response = getOAuth2Response(userRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes());

        // 사용자 정보 로드 또는 생성
        User user = getOrGenerateMember(oAuth2Response);

        // AuthUser 생성 및 반환
        return CustomOAuth2User.fromUser(user);
    }

    private OAuth2Response getOAuth2Response(String registrationId, Map<String, Object> attributes) {
        return switch (registrationId) {
            case "naver" -> new NaverResponse(attributes);
            case "google" -> new GoogleResponse(attributes);
            case "kakao" -> new KakaoResponse(attributes);
            default -> throw new LoginTypeNotSupportException(AuthErrorCode.LOGIN_TYPE_NOT_SUPPORT);
        };
    }

    private User getOrGenerateMember(OAuth2Response oAuth2Response) {
        return userRepository.findBySocialId(oAuth2Response.getProviderId())
                .orElseGet(() -> userRepository.save(User.fromOAuth2Response(oAuth2Response)));
    }
}
