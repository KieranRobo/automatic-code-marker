package com.kieranrobertson.project.controller;

import com.kieranrobertson.project.model.CodingChallenge;
import com.kieranrobertson.project.service.ChallengeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("challenges")
public class ChallengeController {

    @Resource
    private ChallengeService challengeService;

    @GetMapping
    public List<CodingChallenge> allChallenges() {
        return challengeService.getAllChallenges();
    }
}
