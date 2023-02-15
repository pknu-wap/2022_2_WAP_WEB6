package com.web.backend.likedislikecomment;

import com.web.backend.comment.CommentEntity;
import com.web.backend.comment.CommentRepository;
import com.web.backend.likecomment.LikeCommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;

@Service
public class LikeDislikeCommentService {

    @Autowired
    private LikeDislikeCommentRepository likeDislikeCommentRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void toggleComment(HashMap<String, Long> map) {
        Long commentId = map.get("commentId");
        Long userId = map.get("userId");
        Long debateId = map.get("debateId");
        Long status = map.get("status");

        CommentEntity commentEntity = commentRepository.getById(map.get("commentId"));
        LikeDislikeCommentEntity likeDislikeCommentEntity = likeDislikeCommentRepository.getByUserId(map.get("userId"));

        if (likeDislikeCommentEntity == null) {
            likeDislikeCommentEntity = createLikeDislikeComment(commentId, userId, debateId);
        }

        if (status == 1 && (likeDislikeCommentEntity.getStatus() == 0 || likeDislikeCommentEntity.getStatus() == 1)) {
            if (likeDislikeCommentEntity.getStatus() == 0) {
                likeDislikeCommentEntity.setStatus(1);
                commentEntity.setLikeNum(commentEntity.getLikeNum() + 1);
            } else {
                likeDislikeCommentEntity.setStatus(0);
                commentEntity.setLikeNum(commentEntity.getLikeNum() - 1);
            }
        } else if (status == -1 && (likeDislikeCommentEntity.getStatus() == 0 || likeDislikeCommentEntity.getStatus() == -1)) {
            if (likeDislikeCommentEntity.getStatus() == 0) {
                likeDislikeCommentEntity.setStatus(-1);
                commentEntity.setDislikeNum(commentEntity.getDislikeNum() + 1);
            } else {
                likeDislikeCommentEntity.setStatus(0);
                commentEntity.setDislikeNum(commentEntity.getDislikeNum() - 1);
            }
        } else {
            System.out.println("do something important");
        }

        commentRepository.save(commentEntity);
        likeDislikeCommentRepository.save(likeDislikeCommentEntity);

    }

    public LikeDislikeCommentEntity createLikeDislikeComment(Long commentId, Long userId, Long debateId) {

        return LikeDislikeCommentEntity.createLikeDislikeComment(commentId, userId, debateId);
    }
}
