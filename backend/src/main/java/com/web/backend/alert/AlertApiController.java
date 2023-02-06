package com.web.backend.alert;

import com.web.backend.book.BookDto;
import com.web.backend.book.BookService;
import com.web.backend.comment.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AlertApiController {

    @Autowired
    private AlertService alertService;


    @PostMapping("/api/send/alert")
    public void alert(@RequestBody AlertDto dto) {
        alertService.create(dto);
    }

    @GetMapping("/api/get/alerts/{userId}")
    public ResponseEntity<List<AlertDto>> getAlerts(@PathVariable Long userId) {
        List<AlertDto> dtos = alertService.getAlerts(userId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

}
