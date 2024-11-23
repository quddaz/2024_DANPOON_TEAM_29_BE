package com.globalnest.be.user.presentation;

import com.globalnest.be.global.dto.ResponseTemplate;
import com.globalnest.be.global.util.translation.TranslationConverter;
import com.globalnest.be.oauth.dto.CustomOAuth2User;
import com.globalnest.be.user.dto.request.FirstLoginRequest;
import com.globalnest.be.user.dto.response.UserRecommendResponseList;
import com.globalnest.be.user.application.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "User", description = "User API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final TranslationConverter translationConverter;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "첫 로그인 정보 기입")
    public ResponseEntity<?> registerFirstLoginUser(
            @RequestPart FirstLoginRequest request,
            @RequestPart(required = false) MultipartFile file,
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User
    ) {
        userService.registerUser(request, customOAuth2User.getUserId(), file);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }

    @Operation(summary = "유저 구독", description = "유저 구독")
    @GetMapping("/subscribe/{userId}")
    public ResponseEntity<?> subscribeUser(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @PathVariable Long userId
    ) {
        userService.subscribeUser(customOAuth2User.getUserId(), userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }

    @Operation(summary = "유저 추천 리스트", description = "유저 추천 리스트")
    @GetMapping("/recommend")
    public ResponseEntity<?> findUserRecommendList(
            @AuthenticationPrincipal CustomOAuth2User user,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "0") int page
    ) {

        UserRecommendResponseList userRecommendList =
                userService.findUserRecommendList(user.getUserId(), size, page);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(translationConverter.getChatResponse(userRecommendList, user.getLanguage()));
    }
}
