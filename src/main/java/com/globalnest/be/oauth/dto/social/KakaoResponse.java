package com.globalnest.be.oauth.dto.social;


import com.globalnest.be.user.domain.type.OAuthType;

import java.util.Map;


public record KakaoResponse(Map<String, Object> attribute) implements OAuth2Response {

    @Override
    public OAuthType getProvider() {
        return OAuthType.KAKAO;
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        Map<String, Object> account = (Map<String, Object>) attribute.get("kakao_account");
        return account.get("email").toString();
    }

    @Override
    public String getName() {
        Map<String, Object> properties = (Map<String, Object>) attribute.get("properties");
        return properties.get("nickname").toString();
    }

    @Override
    public String getProfileImage() {
        Map<String, Object> account = (Map<String, Object>) attribute.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        // KakaoAccount.Profile.profile_image_url을 우선적으로 참조
        if (profile != null && profile.get("profile_image_url") != null) {
            return profile.get("profile_image_url").toString();
        }

        // fallback으로 Properties의 profile_image를 참조
        Map<String, Object> properties = (Map<String, Object>) attribute.get("properties");
        return properties != null ? properties.getOrDefault("profile_image", "").toString() : "";
    }
}
