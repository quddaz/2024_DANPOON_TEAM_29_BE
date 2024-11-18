package com.globalnest.be.oauth.dto.social;

import com.globalnest.be.user.domain.type.OAuthType;
import java.util.Map;

public record GoogleResponse(Map<String, Object> attribute) implements OAuth2Response {

    @Override
    public OAuthType getProvider() {
        return OAuthType.GOOGLE;
    }

    @Override
    public String getProviderId() {
        return attribute.get("sub").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }

    @Override
    public String getProfileImage() {
        return attribute.get("picture").toString();
    }
}
