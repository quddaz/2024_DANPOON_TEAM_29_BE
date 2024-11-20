package com.globalnest.be.petition.application;

import com.globalnest.be.petition.domain.Agreement;
import com.globalnest.be.petition.domain.Petition;
import com.globalnest.be.petition.dto.request.PetitionUploadRequest;
import com.globalnest.be.petition.repository.AgreementRepository;
import com.globalnest.be.petition.repository.PetitionRepository;
import com.globalnest.be.user.domain.User;
import com.globalnest.be.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PetitionService {

    private final PetitionRepository petitionRepository;
    private final UserService userService;
    private final AgreementRepository agreementRepository;
    @Transactional
    public void uploadPetition(Long userId, PetitionUploadRequest petitionUploadRequest){
        User user = userService.findUserById(userId);

        Petition petition = petitionUploadRequest.toEntity(user);
        Petition newPetition = petitionRepository.save(petition);

        Agreement agreement = Agreement.builder()
            .user(user)
            .petition(newPetition)
            .build();
        agreementRepository.save(agreement);
    }
}
