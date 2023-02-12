package com.web.backend.likecomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LikeCommentApiController {

    @Autowired
    private LikeCommentService likeCommentService;


    @PostMapping("/api/toggle/like/comment/{commentId}/user/{userId}")
    public ResponseEntity<?> likeComment(@PathVariable Long commentId,
                                         @PathVariable Long userId) {

        likeCommentService.toggleLikeComment(commentId, userId);


        return null;
    }

}
