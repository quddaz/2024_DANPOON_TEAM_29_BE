package com.globalnest.be.oauth.dto;

import com.globalnest.be.user.domain.type.Language;
import com.globalnest.be.user.domain.type.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Builder
@Getter
@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User {
    private final Long userId;
    private final String socialId;
    private final String name;
    private final String nickname;
    private final String email;
    private final List<String> roles;
    private final Part part;
    private final Language language;
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

}
