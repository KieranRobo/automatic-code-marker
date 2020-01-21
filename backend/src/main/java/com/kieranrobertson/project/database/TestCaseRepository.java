package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.TestCase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("testCaseRepository")
public interface TestCaseRepository extends CrudRepository<TestCase, Integer> {

}
