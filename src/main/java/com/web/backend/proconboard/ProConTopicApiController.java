package com.web.backend.proconboard;


import com.web.backend.proconboard.ProConTopicDto;
import com.web.backend.proconboard.ProConTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/api/proconTopic/{userId}")
    public ResponseEntity<ProConTopicDto> create(@PathVariable Long userId, @RequestBody ProConTopicDto dto) {
        // 서비스에게 위임
        ProConTopicDto createdDto = proConTopicService.create(userId, dto);

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

}
