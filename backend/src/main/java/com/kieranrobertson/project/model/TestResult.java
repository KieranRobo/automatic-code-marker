package com.kieranrobertson.project.model;

import lombok.Data;

import java.util.Date;

@Data
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
}
