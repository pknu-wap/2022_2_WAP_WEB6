package com.web.backend.api;

import com.web.backend.dto.CommentDto;
import com.web.backend.repository.ProConTopicRepository;
import com.web.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/api/proconTopoic/{proConTopicId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long proConTopicId) {
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(proConTopicId);
        //결과 응답

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //해당 토론 댓글 목록 조회

    // 댓글 생성

    // 댓글 수성

    // 댓글 삭제
}
