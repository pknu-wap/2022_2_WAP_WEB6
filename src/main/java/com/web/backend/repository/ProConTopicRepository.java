package com.web.backend.repository;

import com.web.backend.entity.ProConTopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProConTopicRepository extends JpaRepository<ProConTopicEntity, Long> {


}
