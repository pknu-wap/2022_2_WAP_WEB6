package com.web.backend.repository;

import com.web.backend.entity.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test, Long> { // Test Crud한다. Long은id값

}
