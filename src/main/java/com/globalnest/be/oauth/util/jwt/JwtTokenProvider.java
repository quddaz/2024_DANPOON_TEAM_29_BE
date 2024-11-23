package com.globalnest.be.oauth.util.jwt;

import com.globalnest.be.user.domain.User;
import com.globalnest.be.user.exception.UserNotFoundException;
import com.globalnest.be.user.exception.errorCode.UserErrorCode;
import com.globalnest.be.user.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private Key key;
    private static final String MEMBER_ROLE = "role";

    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;

    @PostConstruct
    public void setKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.secretKey());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * AccessToken 생성 메소드
     */
    public String createAccessToken(Long memberId, List<String> roles) {
        long now = (new Date()).getTime();

        // Access token 유효 기간 설정
        Date accessValidity = new Date(now + jwtProperties.accessTokenExpiration());

        return Jwts.builder()
                .setIssuedAt(new Date(now))
                .setExpiration(accessValidity)
                .setIssuer(jwtProperties.issuer())
                .setSubject(memberId.toString())
                .addClaims(Map.of(MEMBER_ROLE, roles))
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * RefreshToken 생성
     */
    public String createRefreshToken(Long memberId, List<String> roles) {
        long now = (new Date()).getTime();

        // Refresh token 유효 기간 설정
        Date refreshValidity = new Date(now + jwtProperties.refreshTokenExpiration());

        // Refresh token 생성
        String refreshToken = Jwts.builder()
                .setIssuedAt(new Date(now))
                .setExpiration(refreshValidity)
                .setIssuer(jwtProperties.issuer())
                .setSubject(memberId.toString())
                .addClaims(Map.of(MEMBER_ROLE, roles))
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return refreshToken;                   // 쿠키로 반환
    }


    public boolean validateToken(final String token) {
        try {
            log.info("now date: {}", new Date());
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return claims.getBody().getExpiration().after(new Date());
        } catch (Exception e) {
            log.error("Token validation error: ", e);
            return false;
        }
    }

    public User getUser(String token) {
        Long id = Long.parseLong(Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject());

        log.info("in getMember() socialId: {}", id);

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));
    }

    public ResponseCookie createCookie(String refreshToken) {
        return ResponseCookie.from("REFRESH_TOKEN", refreshToken)
                .maxAge(jwtProperties.refreshTokenExpiration() / 1000)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
    }
}