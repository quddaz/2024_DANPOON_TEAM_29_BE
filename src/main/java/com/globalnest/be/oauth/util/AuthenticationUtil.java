package com.globalnest.be.oauth.util;

import com.globalnest.be.oauth.dto.CustomOAuth2User;
import com.globalnest.be.user.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class AuthenticationUtil {

    public static void makeAuthentication(User user) {
        // Authentication 정보 만들기
        CustomOAuth2User authUser = CustomOAuth2User.builder()
            .userId(user.getId())
            .socialId(user.getSocialId())
            .email(user.getEmail())
            .name(user.getName())
            .nickname(user.getNickName())
            .roles(Collections.singletonList(user.getRoleKey()))
            .build();

        // ContextHolder 에 Authentication 정보 저장
        Authentication auth = AuthenticationUtil.getAuthentication(authUser);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private static Authentication getAuthentication(CustomOAuth2User authUser) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authUser.getAuthorities());

        return new UsernamePasswordAuthenticationToken(authUser, "", grantedAuthorities);
    }
}