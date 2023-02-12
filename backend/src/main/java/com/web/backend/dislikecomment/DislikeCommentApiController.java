package com.web.backend.dislikecomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DislikeCommentApiController {

    @Autowired
    private DislikeCommentService dislikeCommentService;


    @PostMapping("/api/toggle/dislike/comment/{commentId}/user/{userId}")
    public ResponseEntity<?> likeComment(@PathVariable Long commentId,
                                         @PathVariable Long userId) {

        dislikeCommentService.toggleDislikeComment(commentId, userId);
        return ResponseEntity.status(HttpStatus.OK).body("success");

    }
}
