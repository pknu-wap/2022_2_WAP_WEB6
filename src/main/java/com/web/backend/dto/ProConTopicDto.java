package com.web.backend.dto;

import com.web.backend.entity.ProConTopicEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ProConTopicDto {

    private Long id;
    private boolean pro_con;
    private String topic;
    private String content;
    private Date due_date;
    private String reason;


    public static ProConTopicDto createProConDto(ProConTopicEntity created) {
        return new ProConTopicDto(
                created.getId(),
                created.isPro_con(),
                created.getTopic(),
                created.getContent(),
                created.getDue_date(),
                created.getReason()
        )
    }
}
