package com.web.backend.dto;

import com.web.backend.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    private Long proConTopicId;
    private String content;
    private boolean proCon;

    // Entity -> dto
    public static CommentDto createCommentDto(CommentEntity c) {
        return new CommentDto(
          c.getId(),
          c.getProConTopic().getId(),
          c.getContent(),
          c.isProCon()
        );
    }
}
