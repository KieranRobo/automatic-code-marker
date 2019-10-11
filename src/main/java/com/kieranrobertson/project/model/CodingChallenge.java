package com.kieranrobertson.project.model;

import com.kieranrobertson.project.service.PythonCommander;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodingChallenge {

    private String name;
    private String destription;
    private String defaultCode;
    private List<TestCase> testCases;

    public CodingChallenge(String name, String description, String defaultCode, List<TestCase> testCases) {
        this.name = name;
        this.destription = description;
        this.defaultCode = defaultCode;
        this.testCases = testCases;
    }

    public Map<TestCase, TestResult> runPython(String pythonCode) {
        Map<TestCase, TestResult> testResults = new HashMap<>();
        PythonCommander pythonCommander = new PythonCommander(pythonCode);

        for (TestCase testCase : testCases) {
            testResults.put(testCase,
                pythonCommander.processTestCase(testCase)
            );
        }
        return testResults;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestription() {
        return destription;
    }

    public void setDestription(String destription) {
        this.destription = destription;
    }

    public String getDefaultCode() {
        return defaultCode;
    }

    public void setDefaultCode(String defaultCode) {
        this.defaultCode = defaultCode;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }
}
