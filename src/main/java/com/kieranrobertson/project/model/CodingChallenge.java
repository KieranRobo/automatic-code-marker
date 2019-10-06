package com.kieranrobertson.project.model;

import java.util.List;

public class CodingChallenge {

    private String name;
    private String destription;
    private String defaultCode;
    private List<TestCase> testCases;

    public CodingChallenge(String name, String destription, String defaultCode, List<TestCase> testCases) {
        this.name = name;
        this.destription = destription;
        this.defaultCode = defaultCode;
        this.testCases = testCases;
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
