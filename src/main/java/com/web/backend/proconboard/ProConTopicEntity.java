package com.web.backend.proconboard;


import com.web.backend.book.BookEntity;
import com.web.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "procon_topic")
public class ProConTopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private boolean pro_con;

    @Column
    private String topic;

    @Column
    private Date due_date;

    @Column
    private String reason;

    @ManyToOne
    @JoinColumn(name = "user_id") //fk
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id") //fk
    private BookEntity book;

    public static ProConTopicEntity createProConTopic(ProConTopicDto dto, UserEntity user, BookEntity book) {

       // 엔티티 생성 및 반환
        return new ProConTopicEntity(
                dto.getId(),
                dto.isPro_con(),
                dto.getTopic(),
                dto.getDue_date(),
                dto.getReason(),
                user,
                book
        );
    }

    public boolean isPro_con() {
        return pro_con;
    }

    public void patch(ProConTopicDto dto) {
        // 예외
        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("찬반 주제 수정 실패 잘못된 id가 입력");
        }

        // 수정
        if (dto.getTopic() != null) {
            this.topic = dto.getTopic();
        }
        if (dto.getReason() != null) {
            this.reason = dto.getReason();
        }

    }
}
