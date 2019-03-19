package io.github.danilkuznetsov.cleverlinks.web.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by danil.kuznetsov on 07/02/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHomePage() throws Exception {

        this.mockMvc.perform(
            get("/")
        )
            .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnLoginPage() throws Exception {

        this.mockMvc.perform(
            get("/login")
        )
            .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn403Page() throws Exception {

        this.mockMvc.perform(
            get("/403")
        )
            .andExpect(status().isOk());
    }

}
