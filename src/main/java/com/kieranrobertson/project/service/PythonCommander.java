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
        return submittedCode += "\nprint(str("+testCase.getMethodName()+"(\""+testCase.getArgument()+"\")))";
    }
}
