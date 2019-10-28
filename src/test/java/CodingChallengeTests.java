import com.kieranrobertson.project.model.CodingChallenge;
import com.kieranrobertson.project.model.TestCase;
import com.kieranrobertson.project.model.TestCaseArgument;
import com.kieranrobertson.project.model.TestResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CodingChallengeTests {

    private CodingChallenge testChallenge1, testChallenge2, testChallenge3;

    @Before
    public void setUpChallenges(){
        List<TestCase> testCases1 = Arrays.asList(
                new TestCase("combineWords", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("firstWord", "STRING", "Hello"),
                        new TestCaseArgument("secondWord", "STRING", "World")
                )), "Hello World"),
                new TestCase("combineWords", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("firstWord", "STRING", "Kieran"),
                        new TestCaseArgument("secondWord", "STRING", "Robertson")
                )), "Kieran Robertson")
        );
        testChallenge1 = new CodingChallenge("Test", "Test", "default code", testCases1);

        /*List<TestCase> testCases2 = Arrays.asList(
                new TestCase("string_test", new Object[] { "\"Hello\"", "\"World\"" }, "Hello World"),
                new TestCase("string_test", new Object[] { "\"Kieran\"", "\"Robertson\"" }, "Kieran Robertson")
        );
        testChallenge2 = new CodingChallenge("Test", "Test", "default code", testCases2);

        List<TestCase> testCases3 = Arrays.asList(
                new TestCase("int_test", new Object[] { 1, 2 }, "3"),
                new TestCase("int_test", new Object[] { 10, -5 }, "5")
        );
        testChallenge3 = new CodingChallenge("Test", "Test", "default code", testCases3);*/
    }

    @Test
    public void correctAnswer_simpleString_test() {
        Map<TestCase, TestResult> results =
                testChallenge1.runCode("def combineWords(firstWord, secondWord):\n" +
                "    return firstWord+\" \"+secondWord",
                CodingChallenge.ProgrammingLanguage.PYTHON);

        for (TestCase testCase : results.keySet()) {
            Assert.assertEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }

   /* @Test
    public void correctAnswer_multiArgString_test() {
        Map<TestCase, TestResult> results =
                testChallenge2.runCode("def string_test(firstWord, secondWord):\n" +
                                "    return firstWord+\" \" + secondWord",
                        CodingChallenge.ProgrammingLanguage.PYTHON);

        for (TestCase testCase : results.keySet()) {
            Assert.assertEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }

    @Test
    public void correctAnswer_multiArgInt_test() {
        Map<TestCase, TestResult> results =
                testChallenge3.runCode("def int_test(num1, num2):\n" +
                                "    return num1+num2",
                        CodingChallenge.ProgrammingLanguage.PYTHON);

        for (TestCase testCase : results.keySet()) {
            Assert.assertEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }*/

    @Test
    public void compilationError_test() {
        boolean compilationResult = testChallenge1.doesCompile("def-ERROR_HERE string_test(word):\n" +
                        "    return word+\" World\"",
                CodingChallenge.ProgrammingLanguage.PYTHON);
        Assert.assertFalse(compilationResult);
    }

}
