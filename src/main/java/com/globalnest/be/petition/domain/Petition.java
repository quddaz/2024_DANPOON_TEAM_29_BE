package com.globalnest.be.petition.domain;

import com.globalnest.be.petition.domain.type.PetitionType;
import com.globalnest.be.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Table(name = "petition")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
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
    @Column(name = "petition_type", nullable = false)
    private PetitionType petitionType;

    @Column(name = "agreement_deadline", nullable = false)
    private LocalDate agreementDeadline;

    @CreatedDate
    @Column(name = "created_date", updatable = false, nullable = false)
    private LocalDate createdDate;

    @Builder
    public Petition(
            User user, String title, String purpose, String content, PetitionType petitionType,
            LocalDate agreementDeadline
    ) {
        this.user = user;
        this.title = title;
        this.purpose = purpose;
        this.content = content;
        this.petitionType = petitionType;
        this.agreementDeadline = agreementDeadline;
    }
}
