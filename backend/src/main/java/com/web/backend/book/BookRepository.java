package com.web.backend.book;

import com.web.backend.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

//    SELECT * FROM BOOK WHERE title=N'데미안' AND authors=N'데미안'
    @Query(value = "SELECT * FROM book WHERE title = :title AND authors = :authors", nativeQuery = true)
    BookEntity checkBook(@Param("title") String title,
                         @Param("authors") String authors
                         );





}
