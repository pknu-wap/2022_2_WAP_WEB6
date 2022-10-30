package com.web.backend.entity;


import com.web.backend.dto.ProConTopicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "procon_topic")
public class ProConTopicEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
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

    public static ProConTopicEntity createProConTopic(ProConTopicDto dto) {

       // 엔티티 생성 및 반환
        return new ProConTopicEntity(
                dto.getId(),
                dto.isPro_con(),
                dto.getTopic(),
                dto.getContent(),
                dto.getDue_date(),
                dto.getReason()

        );
    }

    public boolean isPro_con() {
        return pro_con;
    }
}
