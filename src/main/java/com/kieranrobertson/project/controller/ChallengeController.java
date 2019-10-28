package com.kieranrobertson.project.controller;

import com.kieranrobertson.project.exception.ChallengeNotFoundException;
import com.kieranrobertson.project.model.CodingChallenge;
import com.kieranrobertson.project.model.TestCase;
import com.kieranrobertson.project.service.ChallengeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
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
}
