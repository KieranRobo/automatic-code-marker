package com.kieranrobertson.project.model;

public class TestCase {

    private String methodName;
    private Object[] arguments;
    private String expectedResult;

    public TestCase(String methodName, Object[] arguments, String expectedResult) {
        this.methodName = methodName;
        this.arguments = arguments;
        this.expectedResult = expectedResult;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
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
