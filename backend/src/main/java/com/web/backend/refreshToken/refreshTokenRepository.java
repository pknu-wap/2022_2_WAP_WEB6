package com.web.backend.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface refreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

}
