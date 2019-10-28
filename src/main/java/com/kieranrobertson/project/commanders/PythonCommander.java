package com.kieranrobertson.project.commanders;

import com.kieranrobertson.project.model.TestCase;
import com.kieranrobertson.project.model.TestCaseArgument;
import com.kieranrobertson.project.model.TestResult;
import org.python.util.PythonInterpreter;

import java.util.List;

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
        String placeholder = "\nresult="+testCase.getMethodName()+"({ARGS})";

        StringBuilder args = new StringBuilder();
        for (TestCaseArgument currentArgument : testCase.getArguments()) {
            if (args.length() > 0) {
                args.append(",");
            }
            args.append(currentArgument.getArgumentValue());
        }
        placeholder = placeholder.replace("{ARGS}", args);
        return submittedCode + placeholder;
    }
}
