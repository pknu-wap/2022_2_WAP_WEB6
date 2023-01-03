package com.web.backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

@Component
public class JWTTokenHelper {

    public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60;
    @Value("${jwt.auth.app}")
    private String appName;
    // 비밀키
    @Value("${jwt.auth.secret_key}")
    private String secretKey;
    //만료 시간
    @Value("${jwt.auth.expires_in}")
    private int expiresIn;
    // 암호화 방법
    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    // token으로 사용자 속성정보 조회
    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
    // token 에서 유저이름 가져옴
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    //토큰 생성
    public String generateToken(String username) throws InvalidKeySpecException, NoSuchAlgorithmException {

        return Jwts.builder() // 토큰 만들어줌
                .setIssuer(appName) // app 이름 toast-app
                .setSubject(username) // 사용자 이름
                .setIssuedAt(new Date()) // 현 시간
                .setExpiration(generateExpirationDate()) // 만료 날짜
                .signWith(SIGNATURE_ALGORITHM, secretKey) // 방식
                .compact();
    }

    // JWT accessToken 생성
    public String generateRefreshToken(String username) {
        String refreshToken = Jwts.builder()
                .setIssuer(appName) // app 이름 toast-app
                .setId(username)
                .setIssuedAt(new Date()) // 현 시간
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 5)) // 5시간
                .signWith(SIGNATURE_ALGORITHM, secretKey)
                .compact();

        return refreshToken;
    }

    private Date generateExpirationDate() {
        return new Date(new Date().getTime() + expiresIn * 1000);
    }

    // 토큰 유효성 검증
    public Boolean validateToken(String token, UserDetails userDetails) { 
        final String username = getUsernameFromToken(token);
        return (
                username != null &&
                        username.equals(userDetails.getUsername()) &&
                        !isTokenExpired(token)
        );
    }

    // 토큰 시간 만료 체크
    public boolean isTokenExpired(String token) {
        Date expireDate = getExpirationDate(token);
        return expireDate.before(new Date());
    }

    // 토큰 만료 시간 리턴
    private Date getExpirationDate(String token) {
        Date expireDate;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            expireDate = claims.getExpiration();
        } catch (Exception e) {
            expireDate = null;
        }
        return expireDate;
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    public String getToken(HttpServletRequest request) {

        String authHeader = getAuthHeaderFromHeader(request);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); //Bearer 짜르고 token return
        }

        return null;
    }

    // Authorization header
    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}