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

    private CodingChallenge testChallenge;

    @Before
    public void setUpChallenges(){
        List<TestCase> testCases = Arrays.asList(
                new TestCase("string_test", new String[] { "\"Hello\"" }, "Hello World"),
                new TestCase("string_test", new String[] { "\"Where is the\"" }, "Where is the World")
        );
        testChallenge = new CodingChallenge("Test", "Test", "default code", testCases);
    }

    @Test
    public void correctAnswerTest() {
        Map<TestCase, TestResult> results =  testChallenge.runPython("def string_test(word):\n" +
                "    return word+\" World\"");
        for (TestCase testCase : results.keySet()) {
            Assert.assertEquals(testCase.getExpectedResult(), results.get(testCase).getResult());
        }
    }

}
