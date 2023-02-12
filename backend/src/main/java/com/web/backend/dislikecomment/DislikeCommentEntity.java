package com.web.backend.dislikecomment;

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
@Table(name = "dislike_table")
public class DislikeCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dislike_id")
    private Long id;

    @Column
    @ColumnDefault("0")
    private Integer likeStatus;
    @JoinColumn(name = "user_id")
    private Long userId;

    @JoinColumn(name = "comment_id")
    private Long commentId;
    public static DislikeCommentEntity createDislikeComment(Long commentId, Long userId) {

        return new DislikeCommentEntity(
                0L,
                0,
                userId,
                commentId
        );

    }
}
