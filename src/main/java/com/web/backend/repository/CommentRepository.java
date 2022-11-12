package com.web.backend.repository;

import com.web.backend.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    //찬반 토론 댓글 조회
    @Query(value = "SELECT * FROM comment WHERE procon_topic_id = :proConTopicId", nativeQuery = true)
    List<CommentEntity> findByProConTopicId(@Param("proConTopicId") long proConTopicId);
}
