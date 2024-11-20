package com.globalnest.be.oauth.controller;


import com.globalnest.be.global.dto.ResponseTemplate;
import com.globalnest.be.oauth.dto.CustomOAuth2User;
import com.globalnest.be.oauth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * 테스트 용이기에 지워도 됩니다.
     */
    @GetMapping("/my")
    public ResponseEntity<?> getMy(@AuthenticationPrincipal CustomOAuth2User customOAuth2User){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseTemplate.from(customOAuth2User));
    }

    @GetMapping("/reissue")
    public ResponseEntity<ResponseTemplate<?>> reIssueToken(
        @CookieValue(name = "REFRESH_TOKEN") String refreshToken, HttpServletResponse response) {

        authService.reIssueToken(refreshToken, response);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseTemplate.EMPTY_RESPONSE);
    }

    @GetMapping("/test/{userId}")
    public String test(@PathVariable Long userId){
        return authService.generateTestToken(userId);
    }
}
