package com.web.backend.proconboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProConTopicService {
    @Autowired
    private ProConTopicRepository proConTopicRepository;

    @Transactional
    public ProConTopicDto create( ProConTopicDto dto) {
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
    @Transactional
    public ProConTopicDto delete(Long proconId) {
        ProConTopicEntity target = proConTopicRepository.findById(proconId)
                .orElseThrow(() -> new IllegalArgumentException("찬반 주제 삭제실패, 해당 댓글이 존재하지 않습니다."));

        //삭제 주제 찬반
        proConTopicRepository.delete(target);

        return ProConTopicDto.createProConDto(target);
    }

    public List<ProConTopicDto> topics() {
        // 조회: 댓글 목록
        List<ProConTopicEntity> topics = proConTopicRepository.findAll();

        // 변환 : 엔티티 -> DTO
        List<ProConTopicDto> dtos = new ArrayList<ProConTopicDto>();


        for (int i = 0; i < topics.size(); i++) {
            ProConTopicEntity c = topics.get(i);
            ProConTopicDto dto = ProConTopicDto.createProConDto(c);
            dtos.add(dto);
        }

        return dtos;

    }
}
