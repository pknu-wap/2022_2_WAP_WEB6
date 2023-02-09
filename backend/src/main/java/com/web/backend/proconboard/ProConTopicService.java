package com.web.backend.proconboard;

import com.web.backend.book.BookEntity;
import com.web.backend.book.BookRepository;
import com.web.backend.comment.CommentDto;
import com.web.backend.user.UserDetailsRepository;
import com.web.backend.user.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalDate;
@Slf4j
@Service
public class ProConTopicService {
    @Autowired
    private ProConTopicRepository proConTopicRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public ProConTopicDto create(Long userId, ProConTopicDto dto, Long bookId) {
        // 예외 처리
        // 나중에

        // User Entity 조회
        UserEntity user = userDetailsRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("찬반주제 페이지 생성실패! 대상 유저가 없습"));

        // Book Entity 조회
        BookEntity book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("해당 책 id가 없습니다."));

        // procontopic 엔티티 생성
        ProConTopicEntity proConTopic = ProConTopicEntity.createProConTopic(dto, user, book);
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

    public List<ProConTopicDto> debateTopics(Long bookId) {
        // 조회: 댓글 목록
        List<ProConTopicEntity> topics = proConTopicRepository.getBookDebate(bookId);

        // 변환 : 엔티티 -> DTO
        List<ProConTopicDto> dtos = new ArrayList<ProConTopicDto>();


        for (int i = 0; i < topics.size(); i++) {
            ProConTopicEntity c = topics.get(i);
            ProConTopicDto dto = ProConTopicDto.createProConDto(c);
            dtos.add(dto);
        }

        return dtos;

    }

<<<<<<< HEAD
    public Page<ProConTopicDto> Topics(Long bookId, boolean debateStatus, int offset, int pageSize) {
        offset -= 1;

        LocalDateTime formatedDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String now = formatedDate.format(formatter);

        List<ProConTopicEntity> topics = null;
        // 만료된 토론 목록들
        if (debateStatus) {
            topics = proConTopicRepository.getNotAvailableBookDebate(bookId, now);
        }else {//만료 되지않은 토론 목록
            topics = proConTopicRepository.getAvailableBookDebate(bookId, now);

        }
        
        List<ProConTopicDto> dtos = new ArrayList<ProConTopicDto>();

=======
    //마이페이지 - 토론 조회
    public List<ProConTopicDto> getTopicsByUser(Long userId){
        List<ProConTopicEntity> topics = proConTopicRepository.getTopicByUserId(userId);
        // 변환 : 엔티티 -> DTO
        List<ProConTopicDto> dtos = new ArrayList<ProConTopicDto>();


>>>>>>> feature/due-date
        for (int i = 0; i < topics.size(); i++) {
            ProConTopicEntity c = topics.get(i);
            ProConTopicDto dto = ProConTopicDto.createProConDto(c);
            dtos.add(dto);
        }
<<<<<<< HEAD
        //등록된 순으로 sort
        dtos = dtos.stream().sorted(Comparator.comparing(ProConTopicDto::getId)).collect(Collectors.toList());


        PageRequest pageRequest = PageRequest.of(offset, pageSize);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), dtos.size());
        Page<ProConTopicDto> proconTopics = new PageImpl<>(dtos.subList(start, end), pageRequest, dtos.size());

        return proconTopics;
    }
=======

        return dtos;
    }

>>>>>>> feature/due-date
}
