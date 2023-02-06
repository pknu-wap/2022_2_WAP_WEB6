package com.web.backend.alert;

import com.web.backend.book.BookDto;
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
@Table(name = "user_alert")
public class AlertEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String message;       // 제목
    @Column
    private String url;
    //target
    @ManyToOne
    @JoinColumn(name = "user_id") //fk
    private UserEntity user;
    //sender
    @ManyToOne
    @JoinColumn(name = "sender_id") //fk
    private UserEntity senderId;

    public static AlertEntity createAlert(String message, UserEntity target, UserEntity sender) {
        return new AlertEntity(
                0L,
                null,
                message,
                null,
                target,
                sender
        );
    }
}
