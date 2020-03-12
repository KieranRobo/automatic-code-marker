import com.kieranrobertson.project.Program;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//credit https://dzone.com/articles/rest-endpoint-testing-with-mockmvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ Program.class })
public class ChallengeControllerTests {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void allChallengesLoadsTest() throws Exception {
        this.mockMvc.perform(get("/challenges").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void challengeExists() throws Exception {
        // Assume fibonacci challenge is ID 50
        this.mockMvc.perform(get("/challenges/50").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Fibonacci Sequence"));
    }

    @Test
    public void challengeDoesntExists() throws Exception {
        this.mockMvc.perform(get("/challenges/0").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    /*@Test
    public void createChallengeTest() throws Exception {
        this.mockMvc.perform(post("/challenges")
                .content(asJsonString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/
}
