package com.kieranrobertson.project;

import org.buildobjects.process.ProcBuilder;

public class CodeCommander {

    private String submittedCode;

    public CodeCommander(String submittedCode) {
        this.submittedCode = submittedCode;
    }

    public void process() {
        runShellCommand("echo","Hello World!");
    }

    private void runShellCommand(String cmd, String args) {
        System.out.println(ProcBuilder.run(cmd, args.split(" ")));
    }

}
