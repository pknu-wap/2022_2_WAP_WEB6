package com.web.backend.proconboard;


import com.web.backend.config.APIResponse;
import com.web.backend.proconboard.ProConTopicDto;
import com.web.backend.proconboard.ProConTopicService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j

@RestController // REST API 용 // JSON 반환
//@RequestMapping("/")
public class ProConTopicApiController {

    @Autowired
    private ProConTopicService proConTopicService;

    @GetMapping("/proconTopic/test")
    public String test() {
        return "ProConBoard REST API TEST";
    }

    //생성
    @PostMapping("/api/userId/{userId}/bookId/{bookId}")
    public ResponseEntity<ProConTopicDto> create(@PathVariable Long userId, @RequestBody ProConTopicDto dto, @PathVariable Long bookId) {
        // 서비스에게 위임
        ProConTopicDto createdDto = proConTopicService.create(userId, dto, bookId);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);

    }

    // 모든 procontopic 조회
    @GetMapping("/api/proconTopic/allTopic")
    public ResponseEntity<List<ProConTopicDto>> topics() {
        // 서비스에게 위임
        List<ProConTopicDto> dtos = proConTopicService.topics();
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 만료기간 판별하여 topic 조회
    @GetMapping("/api/proconTopic/{proconId}/status/{debateStatus}/page/{offset}/size/{pageSize}")
    public APIResponse<Page<ProConTopicDto>> availableTopics(@PathVariable Long proconId, @PathVariable boolean debateStatus,
    @PathVariable int offset, @PathVariable int pageSize) {
        Page<ProConTopicDto> topics = proConTopicService.Topics(proconId, debateStatus, offset, pageSize);
//        log.info((Marker) dtos, "INFO");

        return new APIResponse<>(topics.getSize(), topics);
    }



        // procontopic 수정
    @PatchMapping("/api/proconTopic/{proconId}")
    public ResponseEntity<ProConTopicDto> patch(@PathVariable Long proconId,
                                                @RequestBody ProConTopicDto dto) {
        ProConTopicDto createdDto = proConTopicService.update(proconId, dto);

        // 서비스에게 위임
        ProConTopicDto updateDto = proConTopicService.update(proconId, dto);
//        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);

    }



    // 찬반토론 주제 삭제
    @DeleteMapping("/api/proconTopic/{proconId}")
    public ResponseEntity<ProConTopicDto> delete(@PathVariable Long proconId) {
        // 서비스에게 위임
        ProConTopicDto updateDto = proConTopicService.delete(proconId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);

    }

    @GetMapping("/api/proconTopic/{bookId}")
    public ResponseEntity<List<ProConTopicDto>> getBookDebate(@PathVariable Long bookId) {
        // 서비스에게 위임
        List<ProConTopicDto> dtos = proConTopicService.debateTopics(bookId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
}
