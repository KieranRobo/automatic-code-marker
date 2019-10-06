package com.kieranrobertson.project.model;

public class TestCase {
    private String methodName;
    private String argument;
    private String expectedResult;

    public TestCase(String methodName, String argument, String expectedResult) {
        this.methodName = methodName;
        this.argument = argument;
        this.expectedResult = expectedResult;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
