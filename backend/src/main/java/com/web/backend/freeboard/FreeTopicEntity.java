package com.web.backend.freeboard;


import com.web.backend.book.BookEntity;
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
@Table(name = "free_topic")
public class FreeTopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String topic;

    @Column
    private String reason;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Column
    private String book_title;

    public static FreeTopicEntity createFreeTopic(FreeTopicDto dto, UserEntity user, BookEntity book) {

        //엔티티 생성 및 반환
        return new FreeTopicEntity(
                dto.getId(),
                dto.getTopic(),
                dto.getReason(),
                user,
                book,
                book.getTitle()
        );
    }

    public void patch(FreeTopicDto dto) {
        //예외
        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("자유 주제 수정 실패 잘못된 id가 입력");
        }

        //수정
        if (dto.getTopic() != null) {
            this.topic = dto.getTopic();
        }
        if (dto.getReason() != null) {
            this.reason = dto.getReason();
        }

    }


}