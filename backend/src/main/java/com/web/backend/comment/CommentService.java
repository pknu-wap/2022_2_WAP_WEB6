package com.web.backend.comment;

import com.web.backend.proconboard.ProConTopicDto;
import com.web.backend.proconboard.ProConTopicEntity;
import com.web.backend.proconboard.ProConTopicRepository;
import com.web.backend.user.UserDetailsRepository;
import com.web.backend.user.UserEntity;
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
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private ProConTopicRepository proConTopicRepository;
    // 찬반토론 댓글 목록 조회
    public List<CommentDto> proConComments(Long proconId) {
        // 조회: 댓글 목록
        List<CommentEntity> comments = commentRepository.findByProConTopicId(proconId);
        // 변환: 엔티티 -> DTO
        List<CommentDto> dtos = new ArrayList<CommentDto>();

        for (int i = 0; i < comments.size(); i++) {
            CommentEntity c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }

        return dtos;

    }

    @Transactional
    public CommentDto create(Long userId, Long proConTopicId, CommentDto dto) {
        // 게시글 조회 및 예외 처리
        ProConTopicEntity proConTopic = proConTopicRepository.findById(proConTopicId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패, 해당 게시글 없음")); // 없을시 에러 담음

        UserEntity user = userDetailsRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("찬반주제 페이지 생성실패! 대상 유저가 없습"));

        // 댓글 엔티티 생성
        CommentEntity comment = CommentEntity.createComment(dto, user, proConTopic );

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

    public CommentDto delete(Long commentId) {
        // 댓글 조회 예외 발생
        CommentEntity target = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글삭제실패, 해당 댓글이 존재하지 않습니다."));
        // 댓삭
        commentRepository.delete(target);

        // 댓글 to DTO
        return CommentDto.createCommentDto(target);
    }

    //마이페이지 - 댓글 조회
    public List<CommentDto> getCommentsByUser(Long userId) {
        List<CommentEntity> comments = commentRepository.getCommentByUserId(userId);
        // 변환 : 엔티티 -> DTO
        List<CommentDto> dtos = new ArrayList<CommentDto>();


        for (int i = 0; i < comments.size(); i++) {
            CommentEntity c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }

        return dtos;
    }

}
