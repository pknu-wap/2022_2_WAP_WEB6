package com.web.backend.repository;

import com.web.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String userName);
}
