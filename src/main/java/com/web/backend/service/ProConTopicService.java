package com.web.backend.service;

import com.web.backend.dto.ProConTopicDto;
import com.web.backend.entity.CommentEntity;
import com.web.backend.entity.ProConTopicEntity;
import com.web.backend.repository.ProConTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProConTopicService {
    @Autowired
    private ProConTopicRepository proConTopicRepository;

    @Transactional
    public ProConTopicDto create(ProConTopicDto dto) {
        // 예외 처리
        // 나중에

        // procontopic 엔티티 생성
        ProConTopicEntity proConTopic = ProConTopicEntity.createProConTopic(dto);
        // 엔티티 DB에저장
        ProConTopicEntity created = proConTopicRepository.save(proConTopic);

        // DTO로 변경하여 반환
        return ProConTopicDto.createProConDto(created);
    }

    @Transactional
    public ProConTopicDto update(Long proconId, ProConTopicDto dto) {
        // 주제 조회 예외 발생
        ProConTopicEntity target = proConTopicRepository.findById(proconId)
                .orElseThrow(() -> new IllegalArgumentException("찬반토론 주제 수정 실패 해당 주제가 존재하지 않습니다."));
        // 주제 수정
        target.patch(dto);
        // update DB
        ProConTopicEntity updated = proConTopicRepository.save(target);

        return ProConTopicDto.createProConDto(updated);


    }

    public ProConTopicDto delete(Long proconId) {
        ProConTopicEntity target = proConTopicRepository.findById(proconId)
                .orElseThrow(() -> new IllegalArgumentException("찬반 주제 삭제실패, 해당 댓글이 존재하지 않습니다."));

        //삭제 주제 찬반
        proConTopicRepository.delete(target);

        return ProConTopicDto.createProConDto(target);
    }
}
