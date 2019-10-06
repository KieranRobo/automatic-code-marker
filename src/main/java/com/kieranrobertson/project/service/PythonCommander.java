package com.kieranrobertson.project.service;

import com.kieranrobertson.project.model.TestCase;
import org.python.util.PythonInterpreter;

public class PythonCommander {

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

    public void processTestCase(TestCase testCase) {
        try(PythonInterpreter pyInterp = new PythonInterpreter()) {
            String codeWithTestCase = appendTestCase(testCase);
            pyInterp.exec(codeWithTestCase);
        }
    }

    private String appendTestCase(TestCase testCase) {
        String placeholder = "\nprint(str("+testCase.getMethodName()+"({FIRST_ARG})))";
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
