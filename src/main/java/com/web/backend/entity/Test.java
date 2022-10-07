package com.web.backend.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB가 해당 객체를 인식 가능하게 함
@ToString
@AllArgsConstructor
@NoArgsConstructor // 디폴트 생성자 추가
public class Test {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;
//    @ToString
//    @Override
//    public String toString() {
//        return "Test{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
//      @AllArgsConstructor
//    public Test(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
}


