package com.kieranrobertson.project.model;

import com.kieranrobertson.project.service.CodeCommander;
import com.kieranrobertson.project.service.PythonCommander;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodingChallenge {

    public enum ProgrammingLanguage {
        PYTHON
    }

    private String name;
    private String description;
    private String defaultCode;
    private List<TestCase> testCases;

    public CodingChallenge(String name, String description, String defaultCode, List<TestCase> testCases) {
        this.name = name;
        this.description = description;
        this.defaultCode = defaultCode;
        this.testCases = testCases;
    }

    public Map<TestCase, TestResult> runCode(String code, ProgrammingLanguage language) {
        Map<TestCase, TestResult> testResults = new HashMap<>();
        for (TestCase testCase : testCases) {
            testResults.put(testCase,
                    getCommander(language, code).processTestCase(testCase)
            );
        }
        return testResults;
    }

    public boolean doesCompile(String code, ProgrammingLanguage language) {
        return getCommander(language, code).doesCompile();
    }

    private CodeCommander getCommander(ProgrammingLanguage language, String code) {
        CodeCommander codeCommander;
        if (language == ProgrammingLanguage.PYTHON) {
            codeCommander = new PythonCommander(code);
        } // Additional languages must go here
        else {
            throw new RuntimeException("Unknown programming language: " + language.toString());
        }
        return codeCommander;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
