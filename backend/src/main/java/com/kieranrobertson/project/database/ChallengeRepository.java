package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.CodingChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("challengeRepository")
public interface ChallengeRepository extends JpaRepository<CodingChallenge, Integer> {
}
