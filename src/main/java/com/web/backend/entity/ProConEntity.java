package com.web.backend.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity // DB가 해당 객체를 인식 가능하게 함
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "procon_topic")
public class ProConEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private boolean pro_con;

    @Column
    private String topic;

    @Column
    private String content;

    @Column
    private Date due_date;

    @Column
    private String reason;

}
