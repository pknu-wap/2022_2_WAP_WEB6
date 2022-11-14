package com.web.backend.proconboard;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
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
        );
    }
}
