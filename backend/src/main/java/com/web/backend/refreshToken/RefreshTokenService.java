package com.web.backend.refreshToken;

import com.web.backend.book.BookEntity;
import com.web.backend.proconboard.ProConTopicDto;
import com.web.backend.proconboard.ProConTopicEntity;
import com.web.backend.proconboard.ProConTopicRepository;
import com.web.backend.user.UserDetailsRepository;
import com.web.backend.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public Map<String, Object> generateAccessToken(String refreshToken, String accessToken, String userId) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        RefreshTokenEntity target = refreshTokenRepository.findById(Long.valueOf(userId)).get();

        //refresh 토큰 만료 여부

        //refresh 토큰 유효할시 accessToken 재발급
        
        return returnMap;
    }

}
