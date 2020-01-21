package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.TestCaseArgument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("testCaseArgumentRepository")
public interface TestCaseArgumentRepository extends CrudRepository<TestCaseArgument, Integer> {

}
