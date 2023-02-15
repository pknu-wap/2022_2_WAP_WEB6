package com.web.backend.likedislikecomment;

import com.web.backend.likecomment.LikeCommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "like_dislike_table")
public class LikeDislikeCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_dislike_id")
    private Long id;

    @Column
    @ColumnDefault("0")
    private Integer status;

    @JoinColumn(name = "user_id")
    private Long userId;

    @JoinColumn(name = "comment_id")
    private Long commentId;

    @JoinColumn(name = "debate_id")
    private Long debateId;

    public static LikeDislikeCommentEntity createLikeDislikeComment(Long commentId, Long userId, Long debateId) {

        return new LikeDislikeCommentEntity(
                0L,
                0,
                userId,
                commentId,
                debateId
        );

    }
}
