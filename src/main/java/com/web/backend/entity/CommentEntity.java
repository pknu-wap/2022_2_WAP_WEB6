package com.web.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proconTopic_id") //fk
    private ProConTopicEntity proConTopic;

    @Column
    private String content;

    @Column
    private boolean proCon;

    @Column
    private Long likeNum;

    @Column
    private Long dislikeNum;


}
