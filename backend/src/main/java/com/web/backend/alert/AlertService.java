package com.web.backend.alert;

import com.web.backend.book.BookDto;
import com.web.backend.book.BookEntity;
import com.web.backend.book.BookRepository;
import com.web.backend.comment.CommentDto;
import com.web.backend.comment.CommentEntity;
import com.web.backend.user.UserDetailsRepository;
import com.web.backend.user.UserEntity;
import com.web.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AlertService {
    @Autowired
    UserService userService;
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public Boolean create(AlertDto dto) {
        try {
            UserEntity target = userDetailsRepository.findById(dto.getTargetId())
                    .orElseThrow(() -> new IllegalArgumentException("alert 생성 실패 target이 없습니다!"));
            UserEntity sender = userDetailsRepository.findById(dto.getSenderId())
                    .orElseThrow(() -> new IllegalArgumentException("alert 생성 실패 sender이가 없습니다!"));

            AlertEntity alertEntity = AlertEntity.createAlert(dto.getMessage(),target, sender);
            alertRepository.save(alertEntity);

            userService.notificationIncrement(target.getId());

            return true;
        } catch (Exception e) {
            return false;
        }


    }

    public List<AlertDto> getAlerts(Long userId) {
        List<AlertEntity> alerts = alertRepository.findAllByUserId(userId);
        List<AlertDto> dtos = new ArrayList<AlertDto>();

        for (int i = 0; i < alerts.size(); i++) {
            AlertEntity c = alerts.get(i);
            AlertDto dto = AlertDto.createAlertDto(c);
            dtos.add(dto);
        }

        return dtos;
    }

}
