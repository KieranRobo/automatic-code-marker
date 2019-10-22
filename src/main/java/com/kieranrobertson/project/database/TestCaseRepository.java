package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("testCaseRepository")
public interface TestCaseRepository extends JpaRepository<TestCase, Integer> {
}
