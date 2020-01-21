package com.kieranrobertson.project.service;

import com.kieranrobertson.project.database.ChallengeRepository;
import com.kieranrobertson.project.database.TestCaseArgumentRepository;
import com.kieranrobertson.project.database.TestCaseRepository;
import com.kieranrobertson.project.model.CodingChallenge;
import com.kieranrobertson.project.model.TestCase;
import com.kieranrobertson.project.model.TestCaseArgument;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

    @Resource
    private ChallengeRepository challengeRepository;

    @Resource
    private TestCaseRepository testCaseRepository;

    @Resource
    private TestCaseArgumentRepository testCaseArgumentRepository;

    public List<CodingChallenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public Optional<CodingChallenge> getChallenge(int challengeId) {
        return challengeRepository.findById(challengeId);
    }

    public void newChallenge(CodingChallenge challenge) {
        challengeRepository.saveAndFlush(challenge);

        for (TestCase testCase : challenge.getTestCases()) {
            testCaseRepository.save(testCase);
            for (TestCaseArgument argument : testCase.getArguments()) {
                testCaseArgumentRepository.save(argument);
            }
        }


        /*challengeRepository.flush();
        System.out.println("New id:" + createdChallengeTemp.getId());*/
        /*for (TestCase testCase : challenge.getTestCases()) {
            testCase.setCodingChallenge(createdChallengeTemp);
            TestCase createdTestCaseTemp = testCaseRepository.save(testCase);
            for (TestCase testCase : challenge.getTestCases()) {
                testCase.setCodingChallenge(createdChallengeTemp);
                TestCase createdTestCaseTemp = testCaseRepository.save(testCase);

            }
        }*/
    }
}
