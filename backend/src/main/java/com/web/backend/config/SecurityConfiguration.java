package com.web.backend.config;

import com.web.backend.user.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }

    @Autowired
    private CustomUserService userService;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    //암호화 코드
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint).and()
                // 비로그인 유저의 권한
//                .authorizeRequests((request) -> request.antMatchers("/h2-console/**", "/user/login","/api/proconTopic"
//                                ,"/api/proconTopoic/**/comments","/api/proconTopic/**/comments","/api/proconTopic/allTopic","/api/comments/**","/user/register").permitAll() // token 없을 경우
                 .authorizeRequests((request) -> request.antMatchers("/**").permitAll() // token 없을 경우
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()) //token 있을 경우
                .addFilterBefore(new JWTAuthenticationFilter(userService, jwtTokenHelper), // 토큰 검증
                        UsernamePasswordAuthenticationFilter.class);
        // csrf 토큰 해제
        http.csrf().disable().headers().frameOptions().disable();
    }
}
