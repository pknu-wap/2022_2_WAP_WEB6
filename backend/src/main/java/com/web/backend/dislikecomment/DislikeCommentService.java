package com.web.backend.dislikecomment;

import com.web.backend.comment.CommentEntity;
import com.web.backend.comment.CommentRepository;
import com.web.backend.likecomment.LikeCommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DislikeCommentService {

    @Autowired
    private DislikeCommentRepository dislikeCommentRepository;
    @Autowired
    private CommentRepository commentRepository;

    public void toggleDislikeComment(Long commentId, Long userId) {
        CommentEntity commentEntity = commentRepository.getById(commentId);
        DislikeCommentEntity dislikeCommentEntity = dislikeCommentRepository.getByUserId(userId);

        if (dislikeCommentEntity == null) {
            dislikeCommentEntity = createDislikeComent(commentId, userId);
        }

        if (dislikeCommentEntity.getLikeStatus() == 0) {
            dislikeCommentEntity.setLikeStatus(1);
            commentEntity.setLikeNum(commentEntity.getLikeNum() + 1);
        } else {
            dislikeCommentEntity.setLikeStatus(0);
            commentEntity.setLikeNum(commentEntity.getLikeNum() - 1);
        }

        commentRepository.save(commentEntity);
        dislikeCommentRepository.save(dislikeCommentEntity);
    }
    public DislikeCommentEntity createDislikeComent(Long commentId, Long userId) {

        return DislikeCommentEntity.createDislikeComment(commentId, userId);
    }
}
