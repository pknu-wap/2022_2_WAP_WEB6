package com.web.backend.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;
    //해당 토론 댓글 목록 조회
    // 해당 proconTopic 댓글 조회
    @GetMapping("/api/proconTopic/{proconId}/comments")
    public ResponseEntity<List<CommentDto>> proConComments(@PathVariable Long proconId) {
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.proConComments(proconId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/proconTopic/{proConTopicId}/user/{userId}")
    public ResponseEntity<CommentDto> create(@PathVariable Long proConTopicId,
                                             @PathVariable Long userId,
                                             @RequestBody CommentDto dto) {
        // 서비스에게 위임
        CommentDto createdDto = commentService.create(userId, proConTopicId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);

    }

    // 댓글 수정
    @PatchMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentDto> patch(@PathVariable Long commentId,
                                            @RequestBody CommentDto dto) {
        // 서비스에게 위임
        CommentDto updateDto = commentService.update(commentId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);

    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long commentId) {
        // 서비스에게 위임
        CommentDto updateDto = commentService.delete(commentId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);

    }




}
