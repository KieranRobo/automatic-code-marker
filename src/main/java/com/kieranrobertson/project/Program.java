package com.kieranrobertson.project;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Program {

    public static void main(String[] args) {
        PythonCommander codeCommander = new PythonCommander("def my_function() :\n" +
                "    return \"Hello World!\";\n" +
                "\n" +
                "print(my_function())");
        codeCommander.process();
    }
}
