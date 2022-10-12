package com.web.backend.controller;

import com.web.backend.entity.UserEntity;
import com.web.backend.service.UserService;
import com.web.backend.shared.GenericResponse;
import com.web.backend.shared.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/api/1.0/users")
    GenericResponse createUser(@Valid @RequestBody UserEntity user) { //waiting for user

        System.out.println("auth login");
        System.out.println(user);
//        {
//            "userName" : "test12345",
//                "password" : "test12345"
//
//        }
        if(user.getUsername() == null){
            throw new UserNotValidException();
        }
        userService.save(user);
        return new GenericResponse("user saved");
    }
}
