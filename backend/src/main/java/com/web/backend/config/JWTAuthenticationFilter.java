package com.web.backend.config;

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

        if (null != authToken) {
            //token 가져옴
            String userName = jwtTokenHelper.getUsernameFromToken(authToken);

            if (null != userName) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                if (jwtTokenHelper.validateToken(authToken, userDetails)) {

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    //reGenerateRefresh Token
                    jwtTokenHelper.generateRefreshToken(userName);

                }

            }

        }

        filterChain.doFilter(request, response);

    }
}
