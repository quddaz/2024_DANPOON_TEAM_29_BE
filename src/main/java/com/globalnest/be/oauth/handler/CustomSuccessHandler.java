package com.globalnest.be.oauth.handler;

import com.globalnest.be.oauth.dto.CustomOAuth2User;
import com.globalnest.be.oauth.util.jwt.JwtTokenProvider;
import com.globalnest.be.user.exception.UserNotFoundException;
import com.globalnest.be.user.exception.errorCode.UserErrorCode;
import com.globalnest.be.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${app.oauth2.successRedirectUri}")
    private String defaultRedirectUri;
    @Value("${app.oauth2.firstRedirectUri}")
    private String firstRedirectUri;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws IOException {
        CustomOAuth2User authUser = (CustomOAuth2User) authentication.getPrincipal();

        String accessToken = jwtTokenProvider.createAccessToken(authUser.getUserId(), authUser.getRoles());
        String refreshToken = jwtTokenProvider.createRefreshToken(authUser.getUserId(), authUser.getRoles());

        String redirectUrl = determineRedirectUrl(authUser);
        if (redirectUrl.equals(firstRedirectUri)) {
            redirectUrl += "?refreshToken=" + refreshToken;
        } else {
            redirectUrl += "?accessToken=" + accessToken + "&refreshToken=" + refreshToken;
        }

        response.sendRedirect(redirectUrl);
    }

    public String determineRedirectUrl(CustomOAuth2User authUser) {
        return userRepository.findBySocialId(authUser.getSocialId())
            .map(user -> user.getPart() == null ? firstRedirectUri : defaultRedirectUri)
            .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));
    }
}