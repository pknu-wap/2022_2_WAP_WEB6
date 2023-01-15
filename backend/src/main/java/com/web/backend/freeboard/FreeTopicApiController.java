package com.web.backend.freeboard;


import com.web.backend.freeboard.FreeTopicDto;
import com.web.backend.freeboard.FreeTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FreeTopicApiController {

    @Autowired
    private FreeTopicService freeTopicService;

    @GetMapping("/freeTopic/test")
    public String test() {
        return "FreeBoard REST API TEST";
    }

    //생성
    @PostMapping("/api/free/userId/{userId}/bookId/{bookId}")
    public ResponseEntity<FreeTopicDto> create(@PathVariable Long userId, @RequestBody FreeTopicDto dto, @PathVariable Long bookId) {
        FreeTopicDto createdDto = freeTopicService.create(userId, dto, bookId);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);

    }

    //모든 자유토론 조회
    @GetMapping("/api/FreeTopic/allTopic")
    public ResponseEntity<List<FreeTopicDto>> topics() {
        List<FreeTopicDto> dtos = freeTopicService.topics();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    //수정
    @PatchMapping("/api/freeTopic/{freeId}")
    public ResponseEntity<FreeTopicDto> patch(@PathVariable Long freeId,
                                                @RequestBody FreeTopicDto dto) {
        FreeTopicDto createdDto = freeTopicService.update(freeId, dto);

        // 서비스에게 위임
        FreeTopicDto updateDto = freeTopicService.update(freeId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);

    }

    //삭제
    @DeleteMapping("/api/freeTopic/{freeId}")
    public ResponseEntity<FreeTopicDto> delete(@PathVariable Long freeId) {
        //서비스에게 위임
        FreeTopicDto updateDto = freeTopicService.delete(freeId);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);

    }

    @GetMapping("/api/freeTopic/{bookId}")
    public ResponseEntity<List<FreeTopicDto>> getBookDebate(@PathVariable Long bookId) {
        //서비스에게 위임
        List<FreeTopicDto> dtos = freeTopicService.debateTopics(bookId);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
}
