package com.web.backend.service;

import com.web.backend.entity.UserEntity;
import com.web.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
}
