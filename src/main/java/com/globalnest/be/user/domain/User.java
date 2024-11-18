package com.globalnest.be.user.domain;


import com.globalnest.be.user.domain.type.*;
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
    @Column(name = "id")
    private Long id;

    @Column(name = "email", length = 25)
    private String email;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "nick_name", length = 20)
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", length = 20)
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(name = "oauth_type", length = 20)
    private OAuthType oAuthType;

    @Column(name = "social_id", length = 100)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "part", length = 20)
    private Part part;

    @Column(name = "profile_image", length = 200)
    private String profile_image;

    @Column(name = "is_alarm_allowed")
    private Boolean is_alarm_allowed;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_range", length = 20)
    private AgeRange ageRange;

    @Builder
    public User(String email, String name, String nickName, OAuthType oAuthType, String socialId, Role role,
                String profile_image, Language language, Part part, Boolean is_alarm_allowed,
                AgeRange ageRange) {
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.oAuthType = oAuthType;
        this.socialId = socialId;
        this.role = role;
        this.profile_image = profile_image;
        this.language = language;
        this.part = part;
        this.is_alarm_allowed = is_alarm_allowed;
        this.ageRange = ageRange;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
    public List<String> getRoles(){return List.of(this.role.name());}


}