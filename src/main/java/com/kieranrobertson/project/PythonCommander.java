package com.kieranrobertson.project;

import org.python.util.PythonInterpreter;

public class PythonCommander {

    private String submittedCode;

    public PythonCommander(String submittedCode) {
        this.submittedCode = submittedCode;
    }

    public void process() {

        try(PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.exec(submittedCode);
        }
    }


}
