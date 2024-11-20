package com.globalnest.be.user.controller;

import com.globalnest.be.global.application.AWSStorageService;
import com.globalnest.be.global.dto.ResponseTemplate;
import com.globalnest.be.oauth.dto.CustomOAuth2User;
import com.globalnest.be.user.dto.request.FirstLoginRequest;
import com.globalnest.be.user.dto.response.UserRecommendResponseList;
import com.globalnest.be.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    private final AWSStorageService awsStorageService;

    @PostMapping("/first-login")
    @Operation(summary = "첫 로그인 정보 기입")
    public ResponseEntity<?> registerFirstLoginUser(@ModelAttribute FirstLoginRequest request,
                                                    @RequestParam MultipartFile file,
                                                    @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        String file_url = awsStorageService.uploadFile(file, "user");
        userService.registerUser(request, file_url, customOAuth2User.getUserId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.EMPTY_RESPONSE);
    }

    @Operation(summary = "유저 구독", description = "유저 구독")
    @GetMapping("/subscribe/{userId}")
    public ResponseEntity<?> subscribeUser(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                           @PathVariable Long userId) {
        userService.subscribeUser(customOAuth2User.getUserId(), userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.EMPTY_RESPONSE);
    }

    @Operation(summary = "유저 추천 리스트", description = "유저 추천 리스트")
    @GetMapping("/recommend")
    public ResponseEntity<?> findUserRecommendList(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "0") int page
    ) {

        UserRecommendResponseList userRecommendList =
                userService.findUserRecommendList(customOAuth2User.getUserId(), size, page);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(userRecommendList));
    }
}
