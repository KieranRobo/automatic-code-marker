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

    private CodingChallenge testChallenge1, testChallenge2, testChallenge3, arrayChallenge1, arrayChallenge2;

    @Before
    public void setUpChallenges(){
        List<TestCase> testCases1 = Arrays.asList(
                new TestCase("combineWord", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("word", "\"World\"")
                )), "\"Hello World\""),
                new TestCase("combineWord", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("word", "\"Kieran\"")
                )), "\"Hello Kieran\"")
        );
        testChallenge1 = new CodingChallenge("Append to Hello",
                "Append the method argument to the end of 'Hello '",
                "def combineWord(arg):", testCases1);

        List<TestCase> testCases2 = Arrays.asList(
                new TestCase("combineWords", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("firstWord", "\"Hello\""),
                        new TestCaseArgument("secondWord", "\"World\"")
                )), "\"Hello World\""),
                new TestCase("combineWords", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("firstWord", "\"Kieran\""),
                        new TestCaseArgument("secondWord", "\"Robertson\"")
                )), "\"Kieran Robertson\"")
        );
        testChallenge2 = new CodingChallenge("Test", "Test", "default code", testCases2);

        List<TestCase> testCases3 = Arrays.asList(
                new TestCase("int_test", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("num1", "1"),
                        new TestCaseArgument("num2", "2")
                )), "3"),
                new TestCase("int_test", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("num1", "5"),
                        new TestCaseArgument("num2", "-3")
                )), "2")
        );
        testChallenge3 = new CodingChallenge("Test", "Test", "default code", testCases3);


        // Array Tests
        List<TestCase> arrayTestCases1 = Arrays.asList(
                new TestCase("getLength", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("arr", "[]")
                )), "0"),
                new TestCase("getLength", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("arr", "[1,2]")
                )), "2"),
                new TestCase("getLength", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("arr", "[1,2,3]")
                )), "3")
        );
        arrayChallenge1 = new CodingChallenge("Test", "Test", "default code", arrayTestCases1);

        List<TestCase> arrayTestCases2 = Arrays.asList(
                new TestCase("combineArrays", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("arr1", "[1]"),
                        new TestCaseArgument("arr2", "[2]")
                )), "[1,2]"),
                new TestCase("combineArrays", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("arr1", "[]"),
                        new TestCaseArgument("arr2", "[2]")
                )), "[2]"),
                new TestCase("combineArrays", new ArrayList<>(Arrays.asList(
                        new TestCaseArgument("arr1", "[]"),
                        new TestCaseArgument("arr2", "[]")
                )), "[]")
        );
        arrayChallenge2 = new CodingChallenge("Test", "Test", "default code", arrayTestCases2);
    }

    @Test
    public void correctAnswer_simpleString_test() {
        Map<TestCase, TestResult> results =
                testChallenge1.submitAttempt("def combineWord(word):\n" +
                "    return \"Hello \" +word",
                CodingChallenge.ProgrammingLanguage.PYTHON);

        for (TestCase testCase : results.keySet()) {
            Assert.assertEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }

   @Test
    public void correctAnswer_multiArgString_test() {
        Map<TestCase, TestResult> results =
                testChallenge2.submitAttempt("def combineWords(firstWord, secondWord):\n" +
                                "    return firstWord+\" \" + secondWord",
                        CodingChallenge.ProgrammingLanguage.PYTHON);

        for (TestCase testCase : results.keySet()) {
            Assert.assertEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }



    @Test
    public void correctAnswer_multiArgInt_test() {
        Map<TestCase, TestResult> results =
                testChallenge3.submitAttempt("def int_test(num1, num2):\n" +
                                "    return num1+num2",
                        CodingChallenge.ProgrammingLanguage.PYTHON);

        for (TestCase testCase : results.keySet()) {
            Assert.assertEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }


    // Array Tests
    @Test
    public void correctAnswer_arrayLength_test() {
        Map<TestCase, TestResult> results =
                arrayChallenge1.submitAttempt("def getLength(arr):\n" +
                                "    return len(arr)",
                        CodingChallenge.ProgrammingLanguage.PYTHON);

        for (TestCase testCase : results.keySet()) {
            Assert.assertEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }

    @Test
    public void wrongAnswer_arrayLength_test() {
        Map<TestCase, TestResult> results =
                arrayChallenge1.submitAttempt("def getLength(arr):\n" +
                                "    return len(arr)+1;",
                        CodingChallenge.ProgrammingLanguage.PYTHON);

        for (TestCase testCase : results.keySet()) {
            Assert.assertNotEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }

    @Test
    public void correctAnswer_combineArrays_test() {
        Map<TestCase, TestResult> results =
                arrayChallenge2.submitAttempt("def combineArrays(arr1, arr2):\n" +
                                "    return arr1+arr2;",
                        CodingChallenge.ProgrammingLanguage.PYTHON);

        for (TestCase testCase : results.keySet()) {
            Assert.assertEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }

    @Test
    public void wrongAnswer_combineArrays_test() {
        Map<TestCase, TestResult> results =
                arrayChallenge2.submitAttempt("def combineArrays(arr1, arr2):\n" +
                                "    return arr1+arr2+[1];",
                        CodingChallenge.ProgrammingLanguage.PYTHON);

        for (TestCase testCase : results.keySet()) {
            Assert.assertNotEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }


    @Test
    public void compilationError_test() {
        boolean compilationResult = testChallenge1.doesCompile("def-ERROR_HERE string_test(word):\n" +
                        "    return word+\" World\"",
                CodingChallenge.ProgrammingLanguage.PYTHON);
        Assert.assertFalse(compilationResult);
    }



}
