package com.kieranrobertson.project;

import com.kieranrobertson.project.model.CodingChallenge;
import com.kieranrobertson.project.model.TestCase;
import com.kieranrobertson.project.service.Challenge;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Program {

    public static void main(String[] args) {

        TestCase testCase1 = new TestCase("string_test", new String[] { "\"Hello\"" }, "Hello World");
        TestCase testCase2 = new TestCase("string_test", new String[] { "\"Where is the\"" }, "Where in the World");
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(testCase1);
        testCases.add(testCase2);
        CodingChallenge testChallenge = new CodingChallenge("Test", "Test", "default code", testCases);

        Challenge challenge = new Challenge(testChallenge);


        challenge.runPython("def string_test(word):\n" +
                "    return word+\" World!\"");
    }
}
