package com.web.backend.likecomment;

import com.web.backend.comment.CommentEntity;
import com.web.backend.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "like_table")
public class LikeCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @Column
    @ColumnDefault("0")
    private Integer likeStatus;
    @JoinColumn(name = "user_id")
    private Long userId;

    @JoinColumn(name = "comment_id")
    private Long commentId;
//    @ManyToOne
//    @JoinColumn(name = "comment_id")
//    private CommentEntity commentEntity;

//    cascade = CascadeType.PERSIST
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity user;
//


    public static LikeCommentEntity createLikeComment(Long commentId, Long userId) {

        return new LikeCommentEntity(
                0L,
                0,
                userId,
                commentId
        );

    }


//    public static LikeEntity toLikeEntity(MemberEntity memberEntity, BoardEntity boardEntity){
//        LikeEntity likeEntity = new LikeEntity();
//        likeEntity.setMemberEntity(memberEntity);
//        likeEntity.setBoardEntity(boardEntity);
//
//        return likeEntity;
//    }


}