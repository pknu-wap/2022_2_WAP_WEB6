package com.web.backend.api;

import com.web.backend.dto.CommentDto;
import com.web.backend.repository.ProConTopicRepository;
import com.web.backend.service.CommentService;
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

    @GetMapping("/api/proconTopic/{proConTopicId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long proConTopicId) {
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(proConTopicId);
        //결과 응답

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/proconTopic/{proConTopicId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long proConTopicId,
                                             @RequestBody CommentDto dto) {
        // 서비스에게 위임
        CommentDto createdDto = commentService.create(proConTopicId, dto);
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
}
