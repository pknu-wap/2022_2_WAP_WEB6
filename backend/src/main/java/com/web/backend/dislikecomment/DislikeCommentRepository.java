package com.web.backend.dislikecomment;

import com.web.backend.likecomment.LikeCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DislikeCommentRepository extends JpaRepository<DislikeCommentEntity, Long> {
    @Query(value = "SELECT * FROM dislike_table WHERE user_id = :userId", nativeQuery = true)
    DislikeCommentEntity getByUserId(@Param("userId") long userId);
}
