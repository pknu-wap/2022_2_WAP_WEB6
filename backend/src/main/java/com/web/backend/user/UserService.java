package com.web.backend.user;

import com.web.backend.comment.CommentEntity;
import com.web.backend.user.UserEntity;
import com.web.backend.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserEntity save(UserEntity user) {
        //암호화하여 저장
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

    }


    public Boolean checkUserId(UserEntity user) {

        try {
            String userName = user.getUsername();
//        System.out.println(user.getUsername());
            UserEntity result = userRepository.findByUserName(userName);

            return true;

        } catch (Exception e) {
            return false;
        }


//        System.out.println(result);

    }
}
