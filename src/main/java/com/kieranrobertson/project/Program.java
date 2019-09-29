package com.kieranrobertson.project;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Program {

    public static void main(String[] args) {
        CodeCommander codeCommander = new CodeCommander("test");
        codeCommander.process();
    }
}
