package com.web.backend.likecomment;

import com.web.backend.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeCommentRepository extends JpaRepository<LikeCommentEntity, Long> {



    @Query(value = "SELECT * FROM like_table WHERE user_id = :userId", nativeQuery = true)
    LikeCommentEntity getByUserId(@Param("userId") long userId);
}
