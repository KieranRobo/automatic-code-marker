package com.kieranrobertson.project.controller;

import com.kieranrobertson.project.exception.ChallengeNotFoundException;
import com.kieranrobertson.project.exception.CodeCompileException;
import com.kieranrobertson.project.model.CodingChallenge;
import com.kieranrobertson.project.model.TestCase;
import com.kieranrobertson.project.model.TestResult;
import com.kieranrobertson.project.service.ChallengeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Test;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("challenges")
public class ChallengeController {

    @Resource
    private ChallengeService challengeService;

    @GetMapping
    public List<CodingChallenge> allChallenges() {
        return challengeService.getAllChallenges();
    }

    @GetMapping("{id}")
    public CodingChallenge allChallenges(@PathVariable("id") int id) {
        Optional<CodingChallenge> codingChallenge = challengeService.getChallenge(id);
        if (codingChallenge.isPresent()) {
            return codingChallenge.get();
        }
        throw new ChallengeNotFoundException("Challenge with ID " + id + " could not be found.");
    }


    @PostMapping("{id}/attempt")
    public Set<TestCaseResult> attemptChallenge(@PathVariable("id") int id, @RequestBody ChallengeAttempt attempt) {
        Optional<CodingChallenge> codingChallengeCheck = challengeService.getChallenge(id);
        if (!codingChallengeCheck.isPresent()) {
            throw new ChallengeNotFoundException("Challenge with ID " + id + " could not be found.");
        }

        CodingChallenge codingChallenge = codingChallengeCheck.get();
        if (!codingChallenge.doesCompile(attempt.getCode(), attempt.getLanguage())) {
            // TODO: Add compile exception to message.
            throw new CodeCompileException("Code failed to compile.");
        }

        // TODO: move all this out into service and improve logic substantially.
        Set<TestCaseResult> testCaseResults = new HashSet<>();
        Map<TestCase, TestResult> runResults = codingChallenge.runCode(attempt.getCode(), attempt.getLanguage());
        for (TestCase testCase : runResults.keySet()) {
            if (runResults.get(testCase).getResult().equals(testCase.getExpectedResult())) {
                testCaseResults.add(new TestCaseResult(testCase.getId(), true, "Todo output..."));
            } else {
                testCaseResults.add(new TestCaseResult(testCase.getId(), false, "Todo output..."));
            }
        }

        return testCaseResults;
    }

    @Data
    private static class ChallengeAttempt {
        private CodingChallenge.ProgrammingLanguage language;
        private String code;
    }

    @Data
    @AllArgsConstructor
    private static class TestCaseResult {
        private int id;
        private boolean success;
        private String output;
    }


}
