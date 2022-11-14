package com.web.backend.user;

import com.web.backend.user.UserEntity;
import com.web.backend.user.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDetailsRepository.findByUserName(username);

        if (null == user) {
            throw new UsernameNotFoundException("User not found with userName" + username);
        }

        return user;
    }
}
