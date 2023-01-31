package com.web.backend.alert;

import com.web.backend.comment.CommentDto;
import com.web.backend.comment.CommentEntity;
import com.web.backend.user.UserEntity;
import javafx.scene.control.Alert;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class AlertDto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;       // 제목
    private String url;
    private Long targetId;
    private Long senderId;


    public static AlertDto createAlertDto(AlertEntity c) {
        return new AlertDto(
                c.getId(),
                c.getTitle(),
                c.getMessage(),
                c.getUrl(),
                c.getUser().getId(),
                c.getUser().getId()
        );
    }

}
