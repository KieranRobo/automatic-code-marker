import com.kieranrobertson.project.model.CodingChallenge;
import com.kieranrobertson.project.model.TestCase;
import com.kieranrobertson.project.model.TestResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CodingChallengeTests {

    private CodingChallenge testChallenge1, testChallenge2;

    @Before
    public void setUpChallenges(){
        List<TestCase> testCases1 = Arrays.asList(
                new TestCase("string_test", new String[] { "\"Hello\"" }, "Hello World"),
                new TestCase("string_test", new String[] { "\"Where is the\"" }, "Where is the World")
        );
        testChallenge1 = new CodingChallenge("Test", "Test", "default code", testCases1);

        List<TestCase> testCases2 = Arrays.asList(
                new TestCase("string_test", new String[] { "\"Hello\"", "\"World\"" }, "Hello World"),
                new TestCase("string_test", new String[] { "\"Kieran\"", "\"Robertson\"" }, "Kieran Robertson")
        );
        testChallenge2 = new CodingChallenge("Test", "Test", "default code", testCases2);
    }

    @Test
    public void correctAnswerTest() {
        Map<TestCase, TestResult> results =
                testChallenge1.runCode("def string_test(word):\n" +
                "    return word+\" World\"",
                CodingChallenge.ProgrammingLanguage.PYTHON);

        for (TestCase testCase : results.keySet()) {
            Assert.assertEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }

    @Test
    public void correctAnswerMultiArgTest() {
        Map<TestCase, TestResult> results =
                testChallenge2.runCode("def string_test(firstWord, secondWord):\n" +
                                "    return firstWord+\" \" + secondWord",
                        CodingChallenge.ProgrammingLanguage.PYTHON);

        for (TestCase testCase : results.keySet()) {
            Assert.assertEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }

    @Test
    public void testCompileError() {
        boolean compilationResult = testChallenge1.doesCompile("def-ERROR_HERE string_test(word):\n" +
                        "    return word+\" World\"",
                CodingChallenge.ProgrammingLanguage.PYTHON);
        Assert.assertFalse(compilationResult);
    }

}
