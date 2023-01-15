package com.web.backend.freeboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter

public class FreeTopicDto {

    private Long id;
    private String topic;
    private String reason;
    @JsonProperty("user_id")
    private Long userId;
    private Long bookId;
    private String bookTitle;

    public static FreeTopicDto createFreeDto(FreeTopicEntity created) {
        return new FreeTopicDto(
                created.getId(),
                created.getTopic(),
                created.getReason(),
                created.getUser().getId(),
                created.getBook().getId(),
                created.getBook_title()
        );
    }

}