package com.web.backend.refreshToken;

import com.web.backend.proconboard.ProConTopicEntity;
import com.web.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refresh_token")
public class RefreshTokenEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id") //fk
    private UserEntity user;
    private String refreshToken;

    public static RefreshTokenEntity createRefreshToken(UserEntity user, String token) {

        // 엔티티 생성 및 반환
        return new RefreshTokenEntity(
                user.getId(),
                user,
                token
        );
    }
}
