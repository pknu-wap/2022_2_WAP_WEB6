package com.web.backend.freeboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreeTopicRepository extends JpaRepository<FreeTopicEntity, Long> {
    @Query(value = "SELECT * FROM FREE_TOPIC WHERE book_id = :bookid ", nativeQuery = true)
    List<FreeTopicEntity> getBookDebate(@Param("bookid") Long bookid);
}