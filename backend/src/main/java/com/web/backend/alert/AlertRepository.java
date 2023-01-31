package com.web.backend.alert;

import com.web.backend.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlertRepository extends JpaRepository<AlertEntity, Long> {


    @Query(value = "SELECT * FROM user_alert WHERE user_id = :userId", nativeQuery = true)
    List<AlertEntity> findAllByUserId(@Param("userId") long userId);
}
