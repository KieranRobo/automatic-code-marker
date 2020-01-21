package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.CodingChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("challengeRepository")
public interface ChallengeRepository extends JpaRepository<CodingChallenge, Integer> {

}
