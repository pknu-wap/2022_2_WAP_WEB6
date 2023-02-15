package com.web.backend.likedislikecomment;

import com.web.backend.comment.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LikeDislikeCommentApiController {
    @Autowired
    private LikeDislikeCommentService likeDislikeCommentService;
    @PostMapping("/api/toggle/comment/like/dislike")
    public ResponseEntity<?> likeComment(@RequestBody HashMap<String, Long> map) {
        // true or false
        likeDislikeCommentService.toggleComment(map);
        return ResponseEntity.status(HttpStatus.OK).body("success");

    }
}
