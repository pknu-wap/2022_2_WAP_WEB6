package com.web.backend.api;


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

    @PostMapping("api/proconTopic")
    public ResponseEntity<ProConTopicDto> create(@RequestBody ProConTopicDto dto) {

        // 서비스에게 위임
        ProConTopicDto createdDto = proConTopicService.create(dto);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);

    }

}
