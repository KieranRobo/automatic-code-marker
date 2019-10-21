package com.kieranrobertson.project.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="test_cases")
public class TestCase {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="method_name")
    private String methodName;

    private Object[] arguments;

    // Due to current limitation, we always convert result to a String format
    @Column(name="expected_result")
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
