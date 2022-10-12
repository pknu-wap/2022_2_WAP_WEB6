package com.web.backend.config;

import com.web.backend.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //admin 권한 유저 생성
        //memory auth
        auth.inMemoryAuthentication().withUser("Pardeep").password(passwordEncoder().encode("test@123")).authorities("USER", "ADMIN");

        //Database auth
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

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
//        http.authorizeRequests().anyRequest().permitAll(); //모든 권한 ok
//        http.authorizeRequests().anyRequest().authenticated(); //모든 권한 X

//        http.authorizeRequests((request) -> request.antMatchers("/h2-console/**")
//                .permitAll().anyRequest().authenticated()).httpBasic(); //h2-console은 모든 유저가능
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint).and()
                .authorizeRequests((request) -> request.antMatchers("/api/v1/auth/login").permitAll()
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated())
                .addFilterBefore(new JWTAuthenticationFilter(userService, jwtTokenHelper),
                        UsernamePasswordAuthenticationFilter.class);

//        http.formLogin(); //로그인 해라
        http.csrf().disable().headers().frameOptions().disable(); // csrf disable
//        http.httpBasic(); //제일 기본적인 인증 방법 세션, 쿠키 등이 필요 없다, 로그아웃이 불가능하다.
    }
}
