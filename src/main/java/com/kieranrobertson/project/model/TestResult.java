package com.kieranrobertson.project.model;

import java.util.Date;

public class TestResult {

    private TestCase testCase;
    private Date excecutionBeganTime;
    private Object result;

    public TestResult() {
    }

    public TestResult(TestCase testCase, Date excecutionBeganTime, Object result) {
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

    public Object getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
