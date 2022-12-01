package com.web.backend.comment;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
//@Setter
public class CommentDto {
    private Long id;
    private Long proConTopicId;
    private String content;
    private boolean proCon;
    private Long likeNum;
    private Long dislikeNum;
    private Long userId;
    private String userName;

    // Entity -> dto
    public static CommentDto createCommentDto(CommentEntity c) {
        return new CommentDto(
                c.getId(),
                c.getProConTopic().getId(),
                c.getContent(),
                c.isProCon(),
                c.getLikeNum(),
                c.getDislikeNum(),
                c.getUser().getId(),
                c.getUser().getUsername()
        );
    }

    public void setLikeNum(long l) {
        this.likeNum = l;
//        return null;
    }

    public void setDislikeNum(long l) {
        this.likeNum = l;
//        return null;
    }
}
