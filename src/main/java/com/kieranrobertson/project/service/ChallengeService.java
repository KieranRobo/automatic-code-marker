package com.kieranrobertson.project.service;

import com.kieranrobertson.project.database.ChallengeRepository;
import com.kieranrobertson.project.model.CodingChallenge;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

    @Resource
    private ChallengeRepository challengeRepository;

    public List<CodingChallenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public Optional<CodingChallenge> getChallenge(int challengeId) {
        return challengeRepository.findById(challengeId);
    }
}
