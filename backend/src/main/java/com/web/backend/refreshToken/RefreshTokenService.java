package com.web.backend.refreshToken;

import com.web.backend.book.BookEntity;
import com.web.backend.proconboard.ProConTopicDto;
import com.web.backend.proconboard.ProConTopicEntity;
import com.web.backend.proconboard.ProConTopicRepository;
import com.web.backend.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service

public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public ProConTopicDto saveRefreshToken(UserEntity user, String token) {
        // 예외 처리
        // 나중에
        RefreshTokenEntity refreshToken = RefreshTokenEntity.createRefreshToken(user, token);


        RefreshTokenEntity created = refreshTokenRepository.save(refreshToken);



        // DTO로 변경하여 반환
        return null;
    }
}
