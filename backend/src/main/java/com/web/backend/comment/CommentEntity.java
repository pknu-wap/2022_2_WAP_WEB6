package com.web.backend.comment;

import com.web.backend.proconboard.ProConTopicEntity;
import com.web.backend.user.UserEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "procontopic_id") //fk
    private ProConTopicEntity proConTopic;

    @ManyToOne
    @JoinColumn(name = "user_id") //fk
    private UserEntity user;
    @Column
    private String content;

    @Column
    private boolean proCon;

    @Column
    private Long likeNum;

    @Column
    private Long dislikeNum;

    @Column
    private boolean reply;

    @Column
//    private Long parent_comment_id;
    private Long parentCommentId;



    public boolean isProCon() {
        return proCon;
    }

    public boolean isReply() {
        return reply;
    }

    public static CommentEntity createComment(CommentDto dto, UserEntity user, Long parentId, ProConTopicEntity proConTopic) {

        // 예외 발생
        if (dto.getId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패 ! 댓글의id가 없어야합니다");
        }

//        if (dto.getProConTopicId() != proConTopic.getId()) { // 받은 json 이랑 target id다를시
//            throw new IllegalArgumentException("댓글 생성 실패 ! 게시글의 id가 잘못되었습니다");
//        }

        // 엔티티 생성 및 반환
        return new CommentEntity(
                dto.getId(),
                proConTopic,
                user,
                dto.getContent(),
                dto.isProCon(),
                0L,
                0L,
                dto.isReply(),
                parentId
        );

    }

    public void patch(CommentDto dto) {
        // 예외
        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패 잘못된 id가 입력");
        }
        if (this.user.getId() != dto.getUserId()) {
            throw new IllegalArgumentException("댓글 수정 실패 잘못된 id가 입력");
        }

        // 수정
        if (dto.getContent() != null) {
            this.content = dto.getContent();
        }
    }
}
