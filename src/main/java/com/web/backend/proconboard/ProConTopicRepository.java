package com.web.backend.proconboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProConTopicRepository extends JpaRepository<ProConTopicEntity, Long> {


}
