package com.kieranrobertson.project.model;

import java.util.Date;

public class TestResult {

    private TestCase testCase;
    private Date excecutionBeganTime;
    private String result;

    public TestResult() {
    }

    public TestResult(TestCase testCase, Date excecutionBeganTime, String result) {
        this.testCase = testCase;
        this.excecutionBeganTime = excecutionBeganTime;
        this.result = result;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public Date getExcecutionBeganTime() {
        return excecutionBeganTime;
    }

    public void setExcecutionBeganTime(Date excecutionBeganTime) {
        this.excecutionBeganTime = excecutionBeganTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
