package com.web.backend.dto;

import com.web.backend.entity.ProConEntity;
import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@ToString
public class ProConDTO {
    private boolean proCon;
    private String topic;
    private String content;

    private Date dueDate;

    private String reason;


    public ProConEntity toEntity() {
        return new ProConEntity(null, proCon, topic, content, dueDate, reason);
    }
}
