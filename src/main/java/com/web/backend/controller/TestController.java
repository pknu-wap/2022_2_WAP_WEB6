package com.web.backend.controller;

import com.web.backend.dto.TestDto;
import com.web.backend.entity.Test;
import com.web.backend.repository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class TestController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체 자동 연결
    private TestRepository testRepository;

    @PostMapping("/test/create")
    public String createTest(TestDto form) { // TestDto 형태로 받아옴
        System.out.println(form.toString());

        // Entity 자바객체를 DB가 이해 할 수 있게 변환
        // 그리고 Repository를 사용하여 DB에 저장

        // 1. Dto를 Entity로 변환
        Test test = form.toEntity();
        log.info(form.toString());

        // 2. Repository에게 Entity를 DB안에 저장
        Test saved = testRepository.save(test);
        log.info(saved.toString());



        return "";
    }
}
