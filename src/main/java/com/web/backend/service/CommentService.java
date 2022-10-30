package com.web.backend.service;

import com.web.backend.dto.CommentDto;
import com.web.backend.entity.CommentEntity;
import com.web.backend.repository.CommentRepository;
import com.web.backend.repository.ProConTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProConTopicRepository proConTopicRepository;


    public List<CommentDto> comments(Long proConTopicId) {
        // 댓글 목록 조회
        List<CommentEntity> comments = commentRepository.findByProConTopicId(proConTopicId);

        // 엔티티 -> DTO
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++) {
            CommentEntity c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }
        //
        return dtos;
    }
}
