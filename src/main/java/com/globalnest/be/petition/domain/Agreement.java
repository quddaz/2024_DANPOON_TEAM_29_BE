package com.globalnest.be.petition.domain;

import com.globalnest.be.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "agreement")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "petition_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Petition petition;

    @Builder
    public Agreement(User user, Petition petition){
        this.user=user;
        this.petition=petition;
    }
}
