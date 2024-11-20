package com.globalnest.be.petition.presentation;

import com.globalnest.be.global.dto.ResponseTemplate;
import com.globalnest.be.oauth.dto.CustomOAuth2User;
import com.globalnest.be.petition.application.PetitionService;
import com.globalnest.be.petition.domain.Petition;
import com.globalnest.be.petition.dto.request.PetitionUploadRequest;
import com.globalnest.be.post.dto.request.PostUploadRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Petition", description = "청원 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/petitions")
public class PetitionController {

    private final PetitionService petitionService;

    @Operation(summary = "청원 업로드", description = "청원을 업로드합니다<br>")
    @PostMapping
    public ResponseEntity<ResponseTemplate<?>> uploadPetition(
        @Valid @RequestBody PetitionUploadRequest petitionUploadRequest,
        @AuthenticationPrincipal CustomOAuth2User user
    ) {
        petitionService.uploadPetition(user.getUserId(), petitionUploadRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseTemplate.EMPTY_RESPONSE);
    }

}
