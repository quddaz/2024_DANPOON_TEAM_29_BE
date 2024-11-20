package com.globalnest.be.user.domain;

import com.globalnest.be.global.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "subscribe", indexes = {
        @Index(name = "unique_subscribe_user", columnList = "target_user_id, subscribe_user_id", unique = true),
})
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subscribe extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "target_user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User targetUser;

    @JoinColumn(name = "subscribe_user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User subscribeUser;

    @Builder
    public Subscribe(User targetUser, User subscribeUser) {
        this.targetUser = targetUser;
        this.subscribeUser = subscribeUser;
    }

    public static Subscribe of(User targetUser, User subscribeUser) {
        return Subscribe.builder()
                .targetUser(targetUser)
                .subscribeUser(subscribeUser)
                .build();
    }
}
