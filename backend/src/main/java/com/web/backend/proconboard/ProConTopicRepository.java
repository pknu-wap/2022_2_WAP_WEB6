package com.web.backend.proconboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ProConTopicRepository extends JpaRepository<ProConTopicEntity, Long> {

    @Query(value = "SELECT * FROM PROCON_TOPIC WHERE book_id = :bookid ", nativeQuery = true)
    List<ProConTopicEntity> getBookDebate(@Param("bookid") Long bookid);


//    @Query(value = "SELECT * FROM PROCON_TOPIC WHERE book_id = :bookid AND expired = :debateStatus", nativeQuery = true)
//    List<ProConTopicEntity> getAvailableBookDebate(@Param("bookid") Long bookid, @Param("debateStatus") Boolean debateStatus);

    @Query(value = "SELECT * FROM PROCON_TOPIC WHERE due_date >= CURRENT_DATE", nativeQuery = true)
    List<ProConTopicEntity> getAvailableBookDebate(@Param("bookid") Long bookid, @Param("formatedDate") String now);

    @Query(value = "SELECT * FROM PROCON_TOPIC WHERE due_date < CURRENT_DATE", nativeQuery = true)
    List<ProConTopicEntity> getNotAvailableBookDebate(@Param("bookid") Long bookid, @Param("formatedDate") String now);

    //마이페이지 - 토론조회
    @Query(value = "SELECT * FROM PROCON_TOPIC WHERE user_id = :userid ", nativeQuery = true)
    List<ProConTopicEntity> getTopicByUserId(@Param("userid") Long userid);

}
