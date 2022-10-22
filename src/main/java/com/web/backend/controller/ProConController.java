package com.web.backend.controller;


import com.web.backend.dto.ProConDTO;
import com.web.backend.dto.TestDto;
import com.web.backend.entity.Test;
import com.web.backend.repository.ProConRepository;
import com.web.backend.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/procon")
public class ProConController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체 자동 연결
    private ProConRepository proConRepository;

    @PostMapping("/test")
    public String createTest(ProConDTO form) { // TestDto 형태로 받아옴
        System.out.println(form.toString());




        return "";
    }
}
