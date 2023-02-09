package com.web.backend.proconboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class ProConTopicDto {

    private Long id;
    private boolean pro_con;
    private String topic;
    private Date due_date;
    private String reason;
    @JsonProperty("user_id") //json 에서 articleId article_id로 날라온다.
    private Long userId;
    private Long bookId;
    private String bookTitle;

    public static ProConTopicDto createProConDto(ProConTopicEntity created) {
        return new ProConTopicDto(
                created.getId(),
                created.isPro_con(),
                created.getTopic(),
                created.getDue_date(),
                created.getReason(),
                created.getUser().getId(),
                created.getBook().getId(),
                created.getBook_title()
        );
    }
}
