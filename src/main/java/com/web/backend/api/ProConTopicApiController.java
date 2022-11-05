package com.web.backend.api;


import com.web.backend.dto.CommentDto;
import com.web.backend.dto.ProConTopicDto;
import com.web.backend.service.ProConTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // REST API 용 // JSON 반환
@RequestMapping("/")
public class ProConTopicApiController {

    @Autowired
    private ProConTopicService proConTopicService;

    @GetMapping("proconTopic/test")
    public String test() {
        return "ProConBoard REST API TEST";
    }

    //생성
    @PostMapping("api/proconTopic")
    public ResponseEntity<ProConTopicDto> create(@RequestBody ProConTopicDto dto) {

        // 서비스에게 위임
        ProConTopicDto createdDto = proConTopicService.create(dto);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);

    }
    // 댓글 수정
    @PatchMapping("/api/proconTopic/{proconId}")
    public ResponseEntity<ProConTopicDto> patch(@PathVariable Long proconId,
                                            @RequestBody ProConTopicDto dto) {
        ProConTopicDto createdDto = proConTopicService.update(proconId, dto);

        // 서비스에게 위임
        ProConTopicDto updateDto = proConTopicService.update(proconId, dto);
//        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);

    }

//    // 댓글 삭제
//    @DeleteMapping("/api/comments/{commentId}")
//    public ResponseEntity<CommentDto> delete(@PathVariable Long commentId) {
//        // 서비스에게 위임
//        CommentDto updateDto = commentService.delete(commentId);
//        // 결과 응답
//        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
//
//    }

}
