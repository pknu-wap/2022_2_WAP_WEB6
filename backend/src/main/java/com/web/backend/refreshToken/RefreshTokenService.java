package com.web.backend.refreshToken;

import com.web.backend.book.BookEntity;
import com.web.backend.config.JWTTokenHelper;
import com.web.backend.proconboard.ProConTopicDto;
import com.web.backend.proconboard.ProConTopicEntity;
import com.web.backend.proconboard.ProConTopicRepository;
import com.web.backend.user.UserDetailsRepository;
import com.web.backend.user.UserEntity;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@Service

public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Transactional
    public ProConTopicDto saveRefreshToken(UserEntity user, String token) {
        RefreshTokenEntity refreshToken = RefreshTokenEntity.createRefreshToken(user, token);

        RefreshTokenEntity created = refreshTokenRepository.save(refreshToken);

        return null;
    }


}
