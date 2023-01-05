package com.web.backend.user;

import com.web.backend.config.JWTTokenHelper;
import com.web.backend.refreshToken.RefreshTokenService;
import com.web.backend.user.UserEntity;
import com.web.backend.requests.AuthenticationRequest;
import com.web.backend.response.LoginResponse;
import com.web.backend.response.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import static javax.servlet.RequestDispatcher.ERROR_EXCEPTION;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RefreshTokenService refreshTokenService;
    // 로그인 요청
    @PostMapping("/user/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Map<String, Object> result = new HashMap<>();

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUserName(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity user = (UserEntity) authentication.getPrincipal();
        // 해당 유저 토큰 발급
        String jwtToken = jwtTokenHelper.generateToken(user.getUsername());
        // refresh token
        String jwtRefreshToken = jwtTokenHelper.generateRefreshToken(user.getUsername());

        refreshTokenService.saveRefreshToken(user, jwtRefreshToken);


        LoginResponse response = new LoginResponse();



        result.put("jwtToken", jwtToken);
        result.put("jwtRefreshToken", jwtRefreshToken);
        result.put("user", user);


        response.setToken(jwtToken);
        return new ResponseEntity<>(result, HttpStatus.valueOf(200));
    }

    // 모든 유저의 정보 가져오는 요청 test 용
    @GetMapping("/auth/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user) {
        UserEntity userObj = (UserEntity) userDetailsService.loadUserByUsername(user.getName());
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userObj.getFirstName());
        userInfo.setLastName(userObj.getLastName());
        userInfo.setRoles(userObj.getAuthorities().toArray());
        return ResponseEntity.ok(userInfo);
    }
}
