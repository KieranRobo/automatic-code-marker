package com.kieranrobertson.project.commanders;

import com.kieranrobertson.project.model.TestCase;
import com.kieranrobertson.project.model.TestCaseArgument;
import com.kieranrobertson.project.model.TestResult;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

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
            System.out.println(ex.toString());
            return false;
        }
    }

    public TestResult processTestCase(TestCase testCase) {
        log.info("Running code with test case [{}]", testCase);
        TestResult testResult = new TestResult();

        Properties props = new Properties();
        props.put("python.home",System.getenv("PYTHON_HOME"));

        PythonInterpreter.initialize(System.getProperties(), props, new String[0]);

        try(PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.exec(appendTestCase(testCase));

            PyObject resultObject = pyInterp.get("result");

            // Results should always be stringified for compatibility.
            String stringifiedResult = resultObject.toString();
            String returnType = resultObject.getType().getName();

            if (returnType.equals("str")) {
                // Surround output with quotes to signal string.
                stringifiedResult = "\"" + stringifiedResult + "\"";
            } else if (returnType.equals("list")) {
                // Remove spaces so that [1,2] equals [1, 2] for example.
                stringifiedResult = stringifiedResult.replaceAll(" ", "");
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
