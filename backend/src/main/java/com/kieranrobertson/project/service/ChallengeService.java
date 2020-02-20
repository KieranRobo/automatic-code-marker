package com.kieranrobertson.project.service;

import com.kieranrobertson.project.database.*;
import com.kieranrobertson.project.exception.ChallengeNotFoundException;
import com.kieranrobertson.project.exception.ClassNotFoundException;
import com.kieranrobertson.project.exception.InvalidChallengeException;
import com.kieranrobertson.project.model.*;
import com.kieranrobertson.project.model.Class;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLOutput;
import java.util.ArrayList;
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

    @Resource
    private SubmissionAttemptRepository submissionAttemptRepository;

    @Resource
    private ClassRepository classRepository;

    public List<CodingChallenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public Optional<CodingChallenge> getChallenge(int challengeId) {
        return challengeRepository.findById(challengeId);
    }

    public void newChallenge(CodingChallenge challenge) {

        if (challenge.getTestCases().isEmpty()) {
            throw new InvalidChallengeException("No test cases were defined for a new challenge.");
        }

        for (TestCase testCase : challenge.getTestCases()) {
            if (!challenge.getDefaultCode().contains(testCase.getMethodName())) {
                throw new InvalidChallengeException("An assessed method was not found in the default code of a new challenge.");
            }
        }

        // Checks complete, save new challenge.
        challengeRepository.save(challenge);
        for (TestCase testCase : challenge.getTestCases()) {
            testCaseRepository.save(testCase);
            for (TestCaseArgument argument : testCase.getArguments()) {
                testCaseArgumentRepository.save(argument);
            }
        }
    }

    public void saveSubmissionAttempt(int studentId, int challengeId, int testsPassed, String code) {
        SubmissionAttempt submissionAttempt = new SubmissionAttempt(studentId, challengeId, testsPassed, code);
        submissionAttemptRepository.save(submissionAttempt);
    }

    /**
     * Provides list of submissions of a challenge made by students within a class.
     * @param challengeId challenge ID of coding challenge.
     * @param classId class ID containing the students to be checked.
     * @return List of all submissions made by students within classId of challengeId.
     */
    public List<SubmissionAttempt> getSubmissionsFromClass(int challengeId, int classId) {
        Optional<Class> theClassOptional = classRepository.findById(classId);
        Optional<CodingChallenge> codingChallengeOptional = challengeRepository.findById(challengeId);

        if (!theClassOptional.isPresent()) {
            throw new ClassNotFoundException("Class with ID " + classId + " does not exist");
        }
        if (!codingChallengeOptional.isPresent()) {
            throw new ChallengeNotFoundException("Challenge with ID " + challengeId + " does not exist.");
        }
        Class theClass = theClassOptional.get();

        List<SubmissionAttempt> allSubmissions = new ArrayList<>();
        for (Student student : theClass.getStudents()) {
            Optional<SubmissionAttempt> submissionAttemptOptional =
                    submissionAttemptRepository.findById(
                            new SubmissionAttempt.Key(student.getId(), challengeId)
                    );
            if (submissionAttemptOptional.isPresent()) {
                allSubmissions.add(submissionAttemptOptional.get());
            }
            else {
                System.out.println("not exists with key + " + student.getId() + " " + theClass.getId());
            }
        }

        return allSubmissions;
    }
}
