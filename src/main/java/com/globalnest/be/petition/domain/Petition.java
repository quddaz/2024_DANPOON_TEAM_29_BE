package com.globalnest.be.petition.domain;

import com.globalnest.be.petition.domain.type.PetitionType;
import com.globalnest.be.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "petition")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Petition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Column(name = "content", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "petitionType", length = 20)
    private PetitionType petitionType;

    @Column(name = "agreementDeadline", nullable = false)
    private LocalDateTime agreementDeadline;

    @Builder
    public Petition(User user, String title, String purpose, String content, PetitionType petitionType, LocalDateTime agreementDeadline){
        this.user=user;
        this.title=title;
        this.purpose=purpose;
        this.content=content;
        this.petitionType = petitionType;
        this.agreementDeadline = agreementDeadline;
    }
}
