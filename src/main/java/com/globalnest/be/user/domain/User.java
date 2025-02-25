package com.globalnest.be.user.domain;


import com.globalnest.be.oauth.dto.social.OAuth2Response;
import com.globalnest.be.user.domain.type.*;
import com.globalnest.be.user.dto.request.FirstLoginRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "nick_name", nullable = false, length = 40)
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false, length = 20)
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(name = "oauth_type", nullable = false, length = 20)
    private OAuthType oAuthType;

    @Column(name = "social_id", nullable = false, length = 100)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "part", length = 20)
    private Part part;

    @Column(name = "profile_image", nullable = false, length = 200)
    private String profileImage;

    @Column(name = "is_alarm_allowed", nullable = false)
    private Boolean isAlarmAllowed;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_range", length = 20)
    private AgeRange ageRange;

    @Builder
    public User(String email, String name, String nickName, OAuthType oAuthType, String socialId, Role role,
                String profileImage, Language language, Part part, Boolean isAlarmAllowed,
                AgeRange ageRange) {
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.oAuthType = oAuthType;
        this.socialId = socialId;
        this.role = role;
        this.profileImage = profileImage;
        this.language = language;
        this.part = part;
        this.isAlarmAllowed = isAlarmAllowed;
        this.ageRange = ageRange;
    }

    public void updateProfile(FirstLoginRequest request, String profileImage) {
        this.name = request.name();
        this.nickName = request.nickname();
        this.part = request.part();
        this.language = request.language();
        this.ageRange = request.ageRange();

        if (profileImage != null && !profileImage.isBlank()) {
            this.profileImage = profileImage;
        }
    }

    public List<String> getRoles() {
        return List.of(this.role.name());
    }

    public static User fromOAuth2Response(OAuth2Response oAuth2Response) {
        return User.builder()
                .email(oAuth2Response.getEmail())
                .name(oAuth2Response.getName())
                .nickName(oAuth2Response.getName())
                .oAuthType(oAuth2Response.getProvider())
                .socialId(oAuth2Response.getProviderId())
                .role(Role.GUEST)
                .profileImage(oAuth2Response.getProfileImage())
                .language(Language.KOREAN)
                .part(null)
                .isAlarmAllowed(false)
                .ageRange(null)
                .build();
    }
}