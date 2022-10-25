package com.web.backend.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController // REST API 용 // JSON 반환
@RequestMapping("/")
public class ProConBoardApiController{

    @GetMapping("proconboard/test")
    public String test() {
        return "ProConBoard REST API TEST";
    }
}
