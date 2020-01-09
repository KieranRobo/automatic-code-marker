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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public Map<TestCase, Boolean> attemptChallenge(@PathVariable("id") int id, @RequestBody ChallengeAttempt attempt) {
        System.out.println("Attempt:" + attempt.toString());

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
        Map<TestCase, Boolean> outputResults = new HashMap<>();
        Map<TestCase, TestResult> runResults = codingChallenge.runCode(attempt.getCode(), attempt.getLanguage());
        for (TestCase testCase : runResults.keySet()) {
            if (runResults.get(testCase).getResult().equals(testCase.getExpectedResult())) {
                outputResults.put(testCase, true);
            } else {
                outputResults.put(testCase, false);
            }
        }

        return outputResults;
    }

    @Data
    private static class ChallengeAttempt {
        private CodingChallenge.ProgrammingLanguage language;
        private String code;
    }


}
