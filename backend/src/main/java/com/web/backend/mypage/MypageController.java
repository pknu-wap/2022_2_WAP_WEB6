package com.web.backend.mypage;

import com.web.backend.comment.CommentDto;
import com.web.backend.comment.CommentService;
import com.web.backend.proconboard.ProConTopicDto;
import com.web.backend.proconboard.ProConTopicService;
import com.web.backend.user.UserEntity;
import com.web.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 마이페이지) 토론 목록, 댓글 목록 조회
// 사용자 정보 변경(비밀번호, 이메일 변경,탈퇴)
@RestController
public class MypageController {

    @Autowired
    private ProConTopicService proConTopicService;

    @Autowired
    private CommentService commentService;

    @Autowired
    UserService userService;

    @GetMapping("/mypage/{userId}/mytopic")
    public ResponseEntity<List<ProConTopicDto>> getMyTopics(@PathVariable Long userId){
//        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
//        UserEntity user = (UserEntity) auth.getPrincipal();

        List<ProConTopicDto> dtos = proConTopicService.getTopicsByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping("/mypage/{userId}/mycomment")
    public ResponseEntity<List<CommentDto>> getMyComments(@PathVariable Long userId){
//        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
//        UserEntity user = (UserEntity) auth.getPrincipal();

        List<CommentDto> dtos = commentService.getCommentsByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

}
