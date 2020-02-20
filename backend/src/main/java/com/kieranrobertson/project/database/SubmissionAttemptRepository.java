package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.SubmissionAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionAttemptRepository extends JpaRepository<SubmissionAttempt, SubmissionAttempt.Key> {

}
