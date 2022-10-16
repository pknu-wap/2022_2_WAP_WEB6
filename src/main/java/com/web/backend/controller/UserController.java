package com.web.backend.controller;

import com.web.backend.entity.AuthorityEntity;
import com.web.backend.entity.UserEntity;
import com.web.backend.service.UserService;
import com.web.backend.shared.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    // 회원가입
    @PostMapping("/user/register")
    public ResponseEntity createUser(@Valid @RequestBody UserEntity user) { //waiting for user
        List<AuthorityEntity> authorityEntityList = new ArrayList<>();
        authorityEntityList.add(createAuthority("USER", "User role")); //role default 일반유저

        System.out.println(user);

        if(user.getUsername() == null){
            throw new UserNotValidException();
        }
        user.setEnabled(true);
        user.setAuthorities(authorityEntityList);
        userService.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("true");
    }

    private AuthorityEntity createAuthority(String roleCode, String roleDescription) {
        AuthorityEntity authority = new AuthorityEntity();
        authority.setRoleCode(roleCode);
        authority.setRoleDescription(roleDescription);
        return authority;
    }
}
