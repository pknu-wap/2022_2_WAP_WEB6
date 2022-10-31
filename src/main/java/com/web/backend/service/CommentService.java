package com.web.backend.service;

import com.web.backend.dto.CommentDto;
import com.web.backend.entity.CommentEntity;
import com.web.backend.entity.ProConTopicEntity;
import com.web.backend.repository.CommentRepository;
import com.web.backend.repository.ProConTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public CommentDto create(Long proConTopicId, CommentDto dto) {
        // 게시글 조회 및 예외 처리
        ProConTopicEntity proConTopic = proConTopicRepository.findById(proConTopicId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패, 해당 게시글 없음")); // 없을시 에러 담음

        // 댓글 엔티티 생성
        CommentEntity comment = CommentEntity.createComment(dto, proConTopic);

        // 댓글 엔티티를 DB에 저장
        CommentEntity created = commentRepository.save(comment);

        
        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
        
    }

    @Transactional
    public CommentDto update(Long commentId, CommentDto dto) {

        // 댓글 조회 및 예외 처리
        CommentEntity target = commentRepository.findById(commentId).orElseThrow(()
                -> new IllegalArgumentException("댓글 수정 실패 해당 댓글 없음"));
        // 댓글 수정
        target.patch(dto);
        // DB로 갱신
        CommentEntity updated = commentRepository.save(target);
        // 댓글 엔티티 -> DTO
        return CommentDto.createCommentDto(updated);

    }
}
