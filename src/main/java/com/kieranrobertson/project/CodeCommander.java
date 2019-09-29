package com.kieranrobertson.project;

import org.buildobjects.process.ProcBuilder;

public class CodeCommander {

    private final String testMainClass =
            "public class Program { public static void main(String args[]) { System.out.println('This is a mounted program!');}}";

    private String submittedCode;

    public CodeCommander(String submittedCode) {
        this.submittedCode = submittedCode;
    }

    public void process() {
        runShellCommand("mkdir","/opt/app");
        runShellCommand("echo","\""+ testMainClass + "\" > /opt/app/Program.java");
        runShellCommand("docker", "run --rm -v /opt/app:/usr/src/app -w /usr/src/app openjdk:13 java Program");
    }

    private void runShellCommand(String cmd, String args) {
        System.out.println("Running: " + cmd + " " + args);
        System.out.println(ProcBuilder.run(cmd, args.split(" ")));
    }

}
