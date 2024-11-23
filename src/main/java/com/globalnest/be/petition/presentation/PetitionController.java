package com.globalnest.be.petition.presentation;

import com.globalnest.be.oauth.dto.CustomOAuth2User;
import com.globalnest.be.petition.application.PetitionService;
import com.globalnest.be.petition.domain.type.PetitionType;
import com.globalnest.be.petition.dto.request.PetitionUploadRequest;
import com.globalnest.be.petition.dto.response.PetitionDetailResponse;
import com.globalnest.be.petition.dto.response.PetitionResponseList;
import com.globalnest.be.global.util.translation.TranslationConverter;
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
    private final TranslationConverter translationConverter;

    @Operation(summary = "청원 업로드", description = "청원을 업로드합니다<br>")
    @PostMapping
    public ResponseEntity<?> uploadPetition(
        @Valid @RequestBody PetitionUploadRequest petitionUploadRequest,
        @AuthenticationPrincipal CustomOAuth2User user
    ) {
        petitionService.uploadPetition(user.getUserId(), petitionUploadRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null);
    }

    @Operation(summary = "청원 리스트 조회", description = "청원 리스트를 조회합니다<br>"
        + "페이지 번호, 페이지 크기, 정렬 방식을 입력받아 게시글 리스트를 반환합니다<br>"
        + "page는 0번부터 시작")
    @GetMapping
    public ResponseEntity<?> getPetitionMainPage(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "8") int size,
        @RequestParam(required = false) Boolean includeExpired,
        @RequestParam(required = false) PetitionType petitionType,
        @RequestParam(required = false) Boolean sortByAgreementCount,
        @AuthenticationPrincipal CustomOAuth2User user
    ) {
        PetitionResponseList petitionResponseList =
            petitionService.findPetitionResponseList(page, size, includeExpired,
                petitionType, sortByAgreementCount);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(translationConverter.getChatResponse(petitionResponseList, user.getLanguage()));
    }

    @Operation(summary = "청원 상세 페이지 조회", description = "청원 상세 페이지를 조회합니다")
    @GetMapping("/{petitionId}")
    public ResponseEntity<?> getPetitionDetail(
        @PathVariable Long petitionId,
        @AuthenticationPrincipal CustomOAuth2User user
    ) {
        PetitionDetailResponse petitionDetailResponse =
            petitionService.findPetitionDetail(petitionId, user.getUserId());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(translationConverter.getChatResponse(petitionDetailResponse, user.getLanguage()));
    }

    @Operation(summary = "청원 서명 로직", description = "청원 상세 페이지에서 서명을 합니다.")
    @GetMapping("/agreement/{petitionId}")
    public ResponseEntity<?> markingAgreement(
        @PathVariable Long petitionId,
        @AuthenticationPrincipal CustomOAuth2User user
    ) {
        petitionService.markingAgreement(petitionId, user.getUserId());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null);
    }
}
