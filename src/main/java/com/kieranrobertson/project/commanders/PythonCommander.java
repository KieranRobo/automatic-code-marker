package com.kieranrobertson.project.commanders;

import com.kieranrobertson.project.model.TestCase;
import com.kieranrobertson.project.model.TestResult;
import org.python.util.PythonInterpreter;

public class PythonCommander implements CodeCommander {

    private String submittedCode;

    public PythonCommander(String submittedCode) {
        this.submittedCode = submittedCode;
    }

    public boolean doesCompile() {
        try(PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.compile(submittedCode);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public TestResult processTestCase(TestCase testCase) {
        TestResult testResult = new TestResult();
        try(PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.exec(appendTestCase(testCase));

            // Results should always be stringified for compatibility.
            testResult.setResult(pyInterp.get("result").toString());
        }
        return testResult;
    }

    private String appendTestCase(TestCase testCase) {
        String placeholder = "\nresult="+testCase.getMethodName()+"({FIRST_ARG})";
        Object[] arguments = testCase.getArguments();

        for (int i=0; i<arguments.length; i++) {
            if (i==0) {
                placeholder = placeholder.replace("{FIRST_ARG}", arguments[i].toString()+"{ARGS...}");
            } else {
                placeholder = placeholder.replace("{ARGS...}", ","+arguments[i].toString()+"{ARGS...}");
            }
        }
        placeholder = placeholder.replace("{ARGS...}", "");
        return submittedCode + placeholder;
    }
}
