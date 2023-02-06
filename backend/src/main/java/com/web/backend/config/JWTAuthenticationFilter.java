package com.web.backend.config;

import com.web.backend.refreshToken.RefreshTokenService;
import com.web.backend.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;
    private JWTTokenHelper jwtTokenHelper;

    private RefreshTokenService refreshTokenService;

    // 인증에서 제외할 url
//    private static final List<String> EXCLUDE_URL =
//            Collections.unmodifiableList(
//                    Arrays.asList(
////                            "/favicon.ico",
////                            "/admin",
//                    ));

    public JWTAuthenticationFilter(UserDetailsService userDetailsService, JWTTokenHelper jwtTokenHelper) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenHelper = jwtTokenHelper;

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // request 에서 token 가져옴
        String authToken = jwtTokenHelper.getToken(request);

        String userName = null;
        if (null != authToken) {
            //token 가져옴
            userName = jwtTokenHelper.getUsernameFromToken(authToken);
//            System.out.println(userName);
            if (null != userName) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                if (jwtTokenHelper.validateToken(authToken, userDetails)) {

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }

            }

        }
        //reGenerateRefresh Token
//        try {
//            if(userName != null) {
//                System.out.println("------------------------------------------------------");
//                System.out.println("새로운 토큰 생성");
//                System.out.println("------------------------------------------------------");
//                jwtTokenHelper.reGenerateRefreshToken(userName);
//            }
//        }catch (Exception e) {
//            System.out.println("------------------------------------------------------");
//            System.out.println("[JwtRequestFilter] refreshToken 재발급 체크 중 문제 발생 : {}");
//            System.out.println("------------------------------------------------------");
////            log.error("[JwtRequestFilter] refreshToken 재발급 체크 중 문제 발생 : {}", e.getMessage());
//        }


        filterChain.doFilter(request, response);

    }
}
