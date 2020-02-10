package com.kieranrobertson.project.commanders;

import com.kieranrobertson.project.model.TestCase;
import com.kieranrobertson.project.model.TestCaseArgument;
import com.kieranrobertson.project.model.TestResult;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PythonCommander implements CodeCommander {

    private final Logger log = LoggerFactory.getLogger(PythonCommander.class);

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
        log.info("Running code with test case [{}]", testCase);
        TestResult testResult = new TestResult();
        try(PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.exec(appendTestCase(testCase));

            PyObject resultObject = pyInterp.get("result");

            // Results should always be stringified for compatibility.
            String stringifiedResult = resultObject.toString();
            if (resultObject.getType().getName().equals("str")) {
                // Surround output with quotes to signal string.
                stringifiedResult = "\"" + stringifiedResult + "\"";
            }
            testResult.setResult(stringifiedResult);
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
