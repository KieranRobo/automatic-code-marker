package com.kieranrobertson.project.service;

import com.kieranrobertson.project.model.CodingChallenge;
import com.kieranrobertson.project.model.TestCase;

public class Challenge {
    private CodingChallenge codingChallenge;

    public Challenge(CodingChallenge codingChallenge) {
        this.codingChallenge = codingChallenge;
    }

    public void runPython(String pythonCode) {
        PythonCommander pythonCommander = new PythonCommander(pythonCode);
        for (TestCase testCase : codingChallenge.getTestCases()) {
            pythonCommander.processTestCase(testCase);
        }
    }
}
