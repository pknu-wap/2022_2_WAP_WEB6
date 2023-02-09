package com.web.backend.proconboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProConTopicRepository extends JpaRepository<ProConTopicEntity, Long> {

    @Query(value = "SELECT * FROM PROCON_TOPIC WHERE book_id = :bookid ", nativeQuery = true)
    List<ProConTopicEntity> getBookDebate(@Param("bookid") Long bookid);

    //마이페이지 - 토론조회
    @Query(value = "SELECT * FROM PROCON_TOPIC WHERE user_id = :userid ", nativeQuery = true)
    List<ProConTopicEntity> getTopicByUserId(@Param("userid") Long userid);

}
