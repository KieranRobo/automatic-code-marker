package com.kieranrobertson.project.model;

import java.util.Date;

public class TestResult {

    private boolean result;
    private String response;
    private Date excecutionBeganTime;

    public TestResult(boolean result, String response, Date excecutionBeganTime) {
        this.result = result;
        this.response = response;
        this.excecutionBeganTime = excecutionBeganTime;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getExcecutionBeganTime() {
        return excecutionBeganTime;
    }

    public void setExcecutionBeganTime(Date excecutionBeganTime) {
        this.excecutionBeganTime = excecutionBeganTime;
    }
}
