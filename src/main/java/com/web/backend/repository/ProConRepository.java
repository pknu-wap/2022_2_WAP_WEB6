package com.web.backend.repository;

import com.web.backend.entity.ProConEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProConRepository extends JpaRepository<ProConEntity, Long> {



}
