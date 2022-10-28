package com.web.backend.service;

import com.web.backend.dto.ProConTopicDto;
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


}
