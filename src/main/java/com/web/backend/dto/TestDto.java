package com.web.backend.dto;

import com.web.backend.entity.Test;
import lombok.AllArgsConstructor;
import lombok.ToString;

// Dto  : Client에서 data받을 때 Dto로 받음
@AllArgsConstructor
@ToString
public class TestDto {

    private String title;
    private String content;

//     @AllArgsConstructor
//    public TestDto(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
//      @ToString
//    @Override
//    public String toString() {
//        return "TestDto{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }


    public Test toEntity() { //Entity를 새롭게 만들어서 반환
        return new Test(null, title, content);

    }
}
