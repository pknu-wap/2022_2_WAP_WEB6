package com.web.backend.user;

import com.web.backend.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    @Query(value = "SELECT user_name  FROM AUTH_USER_DETAILS  WHERE user_name  = :user", nativeQuery = true)
    UserEntity findByUserName(@Param("user") String user);
    @Query(value = "SELECT * FROM AUTH_USER_DETAILS  WHERE id  = :userId", nativeQuery = true)
    UserEntity findByUserId(Long userId);

//    UserEntity findById(Long userId);
}
