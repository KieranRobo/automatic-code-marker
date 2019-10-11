import com.kieranrobertson.project.model.CodingChallenge;
import com.kieranrobertson.project.model.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CodingChallengeTests {

    private CodingChallenge testChallenge;

    @Before
    public void setUpChallenges(){
        List<TestCase> testCases = Arrays.asList(
                new TestCase("string_test", new String[] { "\"Hello\"" }, "Hello World"),
                new TestCase("string_test", new String[] { "\"Where is the\"" }, "Where in the World")
        );
        testChallenge = new CodingChallenge("Test", "Test", "default code", testCases);
    }

    @Test
    public void correctAnswerTest() {
        testChallenge.runPython("def string_test(word):\n" +
                "    return word+\" World!\"");
    }

}
