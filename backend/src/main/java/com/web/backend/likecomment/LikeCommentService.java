package com.web.backend.likecomment;

import com.web.backend.comment.CommentEntity;
import com.web.backend.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LikeCommentService {
    @Autowired
    private LikeCommentRepository likeCommentRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void toggleLikeComment(Long commentId, Long userId) {
        CommentEntity commentEntity = commentRepository.getById(commentId);
        LikeCommentEntity likeCommentEntity = likeCommentRepository.getByUserId(userId);

        if (likeCommentEntity == null) {
            likeCommentEntity = createLikeComment(commentId, userId);

        }


        if (likeCommentEntity.getLikeStatus() == 0) {
            likeCommentEntity.setLikeStatus(1);
            commentEntity.setLikeNum(commentEntity.getLikeNum() + 1);
        } else {
            likeCommentEntity.setLikeStatus(0);
            commentEntity.setLikeNum(commentEntity.getLikeNum() - 1);
        }

        commentRepository.save(commentEntity);
        likeCommentRepository.save(likeCommentEntity);

    }
    public LikeCommentEntity createLikeComment(Long commentId, Long userId) {
        return LikeCommentEntity.createLikeComment(commentId, userId);
    }
}
