package com.web.backend.user;

import com.web.backend.user.AuthorityEntity;
import com.web.backend.user.UserEntity;
import com.web.backend.user.UserService;
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

        if (user.getUsername() == null) {

            throw new UserNotValidException();

        }
        boolean result = userService.checkUserId(user);
        if (result == true) {
            user.setEnabled(true);
            user.setAuthorities(authorityEntityList);
            userService.save(user);

            return ResponseEntity.status(HttpStatus.OK).body(user.getUsername());
        } else {
            ResponseEntity.status(HttpStatus.OK).body("fail");
        }
        return ResponseEntity.status(HttpStatus.OK).body("sameIdExist");

    }

    private AuthorityEntity createAuthority(String roleCode, String roleDescription) {
        AuthorityEntity authority = new AuthorityEntity();
        authority.setRoleCode(roleCode);
        authority.setRoleDescription(roleDescription);
        return authority;
    }
}
