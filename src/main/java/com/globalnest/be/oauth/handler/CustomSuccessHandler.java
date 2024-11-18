package com.globalnest.be.oauth.handler;

import com.globalnest.be.oauth.dto.CustomOAuth2User;
import com.globalnest.be.oauth.util.jwt.JwtTokenProvider;
import jakarta.servlet.ServletException;
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
    private String redirectUri;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User authUser = (CustomOAuth2User) authentication.getPrincipal();

        String accessToken = jwtTokenProvider.createAccessToken(authUser.getUserId(), authUser.getRoles());
        ResponseCookie refreshToken = jwtTokenProvider.createRefreshToken(authUser.getUserId(), authUser.getRoles());

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.addHeader("Set-Cookie", refreshToken.toString());

        //리다이렉트 정해진 것이 없어 대충
        response.sendRedirect(redirectUri);
    }
}