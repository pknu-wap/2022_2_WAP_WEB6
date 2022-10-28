package com.web.backend.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "procon_topic_board")
public class ProConTopicEntity {


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

    public boolean isPro_con() {
        return pro_con;
    }
}
