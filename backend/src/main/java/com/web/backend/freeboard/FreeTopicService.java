package com.web.backend.freeboard;

import com.web.backend.book.BookEntity;
import com.web.backend.book.BookRepository;
import com.web.backend.freeboard.FreeTopicDto;
import com.web.backend.freeboard.FreeTopicEntity;
import com.web.backend.freeboard.FreeTopicRepository;
import com.web.backend.user.UserDetailsRepository;
import com.web.backend.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FreeTopicService {
    @Autowired
    private FreeTopicRepository freeTopicRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private BookRepository bookRepository;


    @Transactional
    public FreeTopicDto create(Long userId, FreeTopicDto dto, Long bookId) {
        // 예외 처리
        // 나중에

        // User Entity 조회
        UserEntity user = userDetailsRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("자유주제 페이지 생성실패! 대상 유저가 없습"));

        // Book Entity 조회
        BookEntity book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("해당 책 id가 없습니다."));

        // freetopic 엔티티 생성
        FreeTopicEntity freeTopic = FreeTopicEntity.createFreeTopic(dto, user, book);

        // 엔티티 DB에저장
        FreeTopicEntity created = freeTopicRepository.save(freeTopic);

        // DTO로 변경하여 반환
        return FreeTopicDto.createFreeDto(created);
    }

    @Transactional
    public FreeTopicDto update(Long freeId, FreeTopicDto dto) {
        // 주제 조회 예외
        FreeTopicEntity target = freeTopicRepository.findById(freeId)
                .orElseThrow(() -> new IllegalArgumentException("자유토론 주제 수정 실패 해당 주제가 존재하지 않습니다."));
        // 주제 수정
        target.patch(dto);
        // update DB
        FreeTopicEntity updated = freeTopicRepository.save(target);

        return FreeTopicDto.createFreeDto(updated);


    }
    @Transactional
    public FreeTopicDto delete(Long freeId) {
        FreeTopicEntity target = freeTopicRepository.findById(freeId)
                .orElseThrow(() -> new IllegalArgumentException("자유 주제 삭제실패, 해당 댓글이 존재하지 않습니다."));

        //삭제
        freeTopicRepository.delete(target);

        return FreeTopicDto.createFreeDto(target);
    }



    public List<FreeTopicDto> topics() {
        // 조회: 댓글 목록
        List<FreeTopicEntity> topics = freeTopicRepository.findAll();

        // 변환 : 엔티티 -> DTO
        List<FreeTopicDto> dtos = new ArrayList<FreeTopicDto>();


        for (int i = 0; i < topics.size(); i++) {
            FreeTopicEntity c = topics.get(i);
            FreeTopicDto dto = FreeTopicDto.createFreeDto(c);
            dtos.add(dto);
        }

        return dtos;

    }
    public List<FreeTopicDto> debateTopics(Long bookId) {
        // 조회: 댓글 목록
        List<FreeTopicEntity> topics = freeTopicRepository.getBookDebate(bookId);

        // 변환 : 엔티티 -> DTO
        List<FreeTopicDto> dtos = new ArrayList<FreeTopicDto>();


        for (int i = 0; i < topics.size(); i++) {
            FreeTopicEntity c = topics.get(i);
            FreeTopicDto dto = FreeTopicDto.createFreeDto(c);
            dtos.add(dto);
        }

        return dtos;

    }



}